package org.aemudapi.user.mapper;

import org.aemudapi.user.dtos.UserResponseDto;
import org.aemudapi.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    /**
     * Convertit une entité User en un DTO UserDtoResponse.
     * C'est la méthode la plus courante pour envoyer les données utilisateur au client.
     *
     * @param user L'entité User à convertir.
     * @return Le DTO UserDtoResponse correspondant, ou null si l'entrée est null.
     */
    public UserResponseDto toDto(User user) {
        if (user == null) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setUsername(user.getUsername());
        userResponseDto.setLocked(user.isLocked());
        userResponseDto.setForcePasswordChange(user.isForcePasswordChange());
        userResponseDto.setMemberId(user.getMember().getId()); // Assurez-vous que l'objet Member est également mappable si nécessaire
        userResponseDto.setRoles(user.getRoles());   // Assurez-vous que les objets Role sont également mappables si nécessaire

        return userResponseDto;
    }

    /**
     * Convertit un DTO UserDtoResponse en une entité User.
     * Cette méthode est moins courante pour un DTO de "réponse",
     * mais incluse pour illustrer le mappage inverse.
     * En général, on utiliserait un UserDtoRequest pour créer/mettre à jour des entités.
     *
     * @param userResponseDto Le DTO UserDtoResponse à convertir.
     * @return L'entité User correspondante, ou null si l'entrée est null.
     */
    public User toEntity(UserResponseDto userResponseDto) {
        if (userResponseDto == null) {
            return null;
        }

        User user = new User();
        user.setId(userResponseDto.getId()); // Pour les mises à jour, sinon laissé null pour les créations
        user.setUsername(userResponseDto.getUsername());
        user.setLocked(userResponseDto.isLocked());
        user.setForcePasswordChange(userResponseDto.isForcePasswordChange());
        //  user.setMember(userResponseDto.getMember());
        user.setRoles(userResponseDto.getRoles());
        // Note: Le mot de passe n'est pas dans UserDtoResponse, donc il ne peut pas être mappé ici.
        // C'est une raison pour laquelle un UserDtoRequest serait plus approprié pour la création/mise à jour.

        return user;
    }

    /**
     * Convertit une liste d'entités User en une liste de DTOs UserDtoResponse.
     *
     * @param users La liste d'entités User à convertir.
     * @return La liste de DTOs UserDtoResponse correspondante.
     */
    public List<UserResponseDto> toDtoList(List<User> users) {
        if (users == null) {
            return null;
        }
        return users.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Convertit une liste de DTOs UserDtoResponse en une liste d'entités User.
     * Moins courante pour les DTOs de réponse, mais incluse pour la complétude.
     *
     * @param userResponsDtos La liste de DTOs UserDtoResponse à convertir.
     * @return La liste d'entités User correspondante.
     */
    public List<User> toEntityList(List<UserResponseDto> userResponsDtos) {
        if (userResponsDtos == null) {
            return null;
        }
        return userResponsDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}