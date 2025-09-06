package org.aemudapi.user.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aemudapi.member.entity.Member;
import org.aemudapi.member.repository.MemberRepository;
import org.aemudapi.notification.services.EmailService;
import org.aemudapi.user.dtos.ChangeForgottenPassword;
import org.aemudapi.user.dtos.ChangePasswordRequestDTO;
import org.aemudapi.user.dtos.UserRequestDto;
import org.aemudapi.user.dtos.UserResponseDto;
import org.aemudapi.user.entity.PasswordResetToken;
import org.aemudapi.user.entity.Role;
import org.aemudapi.user.entity.User;
import org.aemudapi.user.mapper.UserMapper;
import org.aemudapi.user.repository.PasswordResetTokenRepository;
import org.aemudapi.user.repository.RoleRepository;
import org.aemudapi.user.repository.UserRepository;
import org.aemudapi.user.services.UserService;
import org.aemudapi.utils.ResponseVO;
import org.aemudapi.utils.ResponseVOBuilder;
import org.aemudapi.utils.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.rmi.server.LogStream.log;
import static org.aemudapi.user.repository.PasswordResetTokenRepository.*;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    @Transactional
    public ResponseEntity<ResponseVO<Void>> createUser(UserRequestDto userRequestDto) {
        Member member = memberRepository.findById(userRequestDto.getMemberId()).orElse(null);
        if (member == null) {
            return new ResponseEntity<>(new ResponseVOBuilder<Void>().fail(HttpStatus.BAD_REQUEST).build(), HttpStatus.BAD_REQUEST);
        }

        // Vérifie si un utilisateur existe déjà avec cet email
        if (userRepository.findByUsername(member.getContactInfo().getEmail()).isPresent()) {
            return new ResponseEntity<>(new ResponseVOBuilder<Void>().fail(HttpStatus.BAD_REQUEST).build(), HttpStatus.BAD_REQUEST);
        }

        String temporaryPassword = "passer";

        // Création de l'utilisateur
        User user = new User();
        user.setUsername(member.getContactInfo().getEmail());
        user.setPassword(passwordEncoder.encode(temporaryPassword));
        user.setMember(member);
        user.setForcePasswordChange(true);

        List<Role> roles = Arrays.stream(userRequestDto.getRoles()).map(roleEnum -> roleRepository.findByName(roleEnum.name()).orElseThrow(() -> new EntityNotFoundException("Role not found: " + roleEnum.name()))).collect(Collectors.toList());

        user.setRoles(roles);

        userRepository.save(user);
        String subject = "Bienvenue sur AEMUD";
        String text = "Bonjour \n\nVotre compte a été créé avec succès. Voici votre mot de passe temporaire : \n\nVeuillez le changer à votre première connexion.";
        sendEmail(member.getContactInfo().getEmail(), subject, text);

        return new ResponseEntity<>(new ResponseVOBuilder<Void>().success().build(), HttpStatus.CREATED);
    }

    private void sendEmail(String email, String subject, String message) {


        emailService.sendSimpleMessage(email, subject, message);
    }

    @Override
    public ResponseEntity<ResponseVO<UserResponseDto>> getUserByUsername(String username) {
        User user = this.userRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
        return new ResponseEntity<>(new ResponseVOBuilder<UserResponseDto>().addData(this.userMapper.toDto(user)).build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<Void>> changePasswordFirstConnection(ChangePasswordRequestDTO changePasswordRequestDTO) {
        User user = this.userRepository.findByUsername(changePasswordRequestDTO.username()).orElseThrow(EntityNotFoundException::new);
//        if (!passwordEncoder.matches(changePasswordRequestDTO.oldPassword(), user.getPassword())) {
//            return new ResponseEntity<>(new ResponseVOBuilder<Void>().fail(HttpStatus.BAD_REQUEST).build(), HttpStatus.BAD_REQUEST);
//        }
        if (!changePasswordRequestDTO.password().equals(changePasswordRequestDTO.confirmPassword())) {
            return new ResponseEntity<>(new ResponseVOBuilder<Void>().fail(HttpStatus.BAD_REQUEST).build(), HttpStatus.BAD_REQUEST);
        }
        user.setPassword(passwordEncoder.encode(changePasswordRequestDTO.password()));
        user.setForcePasswordChange(false);
        this.userRepository.save(user);
        return new ResponseEntity<>(new ResponseVOBuilder<Void>().success().build(), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseVO<Boolean>> checkIfEmailValid(String email) {
        Optional<User> userOpt = this.userRepository.findByUsername(email);
        if (userOpt.isEmpty()) {
            return new ResponseEntity<>(new ResponseVOBuilder<Boolean>().fail(HttpStatus.BAD_REQUEST).build(), HttpStatus.BAD_REQUEST);
        }

        // Génération du code
        String code = Utils.generateResetCode();
        PasswordResetToken token = new PasswordResetToken();
        token.setEmail(email);
        token.setToken(code);
        token.setUsed(false);
        token.setExpiryDate(LocalDateTime.now().plusMinutes(10));
        this.passwordResetTokenRepository.save(token);

        sendEmail(email, "Code de validation de votre email", "Votre code de validation est : " + code + " (valide 10 min)");

        return new ResponseEntity<>(new ResponseVOBuilder<Boolean>().success().build(), HttpStatus.OK);
    }

    @Override
    public Boolean checkTokenValid(String email, String token) {
        PasswordResetToken tokenEntity = passwordResetTokenRepository.findByTokenAndEmail(token, email)
                .orElseThrow(() -> new RuntimeException("Code invalide"));

        return !tokenEntity.getExpiryDate().isBefore(LocalDateTime.now()) && !tokenEntity.isUsed();
    }

    @Override
    public ResponseEntity<ResponseVO<Void>> changeChangeWordPForgotten(ChangeForgottenPassword forgottenPassword) {
        if (checkTokenValid(forgottenPassword.username(), forgottenPassword.token())) {
            User user = this.userRepository.findByUsername(forgottenPassword.username()).orElseThrow(EntityNotFoundException::new);
            if (forgottenPassword.password().equals(forgottenPassword.confirmPassword())) {
                user.setPassword(passwordEncoder.encode(forgottenPassword.password()));
                PasswordResetToken usedToken = this.passwordResetTokenRepository.findByTokenAndEmail(forgottenPassword.token(), forgottenPassword.username()).orElseThrow(EntityNotFoundException::new);
                usedToken.setUsed(true);
                this.passwordResetTokenRepository.save(usedToken);
                this.userRepository.save(user);
                return new ResponseEntity<>(new ResponseVOBuilder<Void>().success().build(), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ResponseVOBuilder<Void>().fail(HttpStatus.BAD_REQUEST).build(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseVOBuilder<Void>().fail(HttpStatus.BAD_REQUEST).build(), HttpStatus.BAD_REQUEST);
    }
}
