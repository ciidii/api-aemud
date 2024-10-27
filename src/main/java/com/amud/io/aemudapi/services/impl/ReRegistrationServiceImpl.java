package com.amud.io.aemudapi.services.impl;

import com.amud.io.aemudapi.dto.MemberRequestDto;
import com.amud.io.aemudapi.dto.ReRegistrationDto;
import com.amud.io.aemudapi.dto.RegistrationPearYearDto;
import com.amud.io.aemudapi.dto.RegistrationResponseDto;
import com.amud.io.aemudapi.entities.MemberAndYearKey;
import com.amud.io.aemudapi.entities.MemberHasBourse;
import com.amud.io.aemudapi.entities.MemberHasBourseKey;
import com.amud.io.aemudapi.entities.ReRegistration;
import com.amud.io.aemudapi.exceptions.customeExceptions.UserAlreadyExistsException;
import com.amud.io.aemudapi.mappers.MemberHasBourseKeyMapper;
import com.amud.io.aemudapi.mappers.MemberMapper;
import com.amud.io.aemudapi.mappers.ReRegistrationKeyMapper;
import com.amud.io.aemudapi.mappers.ReRegistrationMapper;
import com.amud.io.aemudapi.repositories.MemberHasBourseRepository;
import com.amud.io.aemudapi.repositories.MemberRepository;
import com.amud.io.aemudapi.repositories.ReRegistrationRepository;
import com.amud.io.aemudapi.services.ReRegistrationService;
import com.amud.io.aemudapi.utils.ResponseVO;
import com.amud.io.aemudapi.utils.ResponseVOBuilder;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReRegistrationServiceImpl implements ReRegistrationService {
    private final ReRegistrationMapper registrationMapper;
    private final ReRegistrationRepository reRegistrationRepository;
    private final ReRegistrationKeyMapper reRegistrationKeyMapper;
    private final MemberMapper memberMapper;
    private final MemberRepository memberRepository;
    private final MemberHasBourseRepository memberHasBourseRepository;
    private final MemberHasBourseKeyMapper memberHasBourseKeyMapper;

    public ReRegistrationServiceImpl(ReRegistrationMapper registrationMapper, ReRegistrationRepository reRegistrationRepository1, ReRegistrationKeyMapper reRegistrationKeyMapper, MemberMapper memberMapper, MemberRepository memberRepository, MemberHasBourseRepository memberHasBourseRepository, MemberHasBourseKeyMapper memberHasBourseKeyMapper) {
        this.registrationMapper = registrationMapper;
        this.reRegistrationRepository = reRegistrationRepository1;
        this.reRegistrationKeyMapper = reRegistrationKeyMapper;
        this.memberMapper = memberMapper;
        this.memberRepository = memberRepository;
        this.memberHasBourseRepository = memberHasBourseRepository;
        this.memberHasBourseKeyMapper = memberHasBourseKeyMapper;
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseVO<RegistrationResponseDto>> reRegisterMember(ReRegistrationDto reRegistrationDto) {
        ReRegistration reRegistration = this.registrationMapper.toEntity(reRegistrationDto);
        MemberAndYearKey memberAndYearKey = this.reRegistrationKeyMapper.toEntity(reRegistrationDto.getReRegistrationKeyDto());

        this.memberRepository.findById(memberAndYearKey.getMember()).orElseThrow(() -> {
            throw new EntityNotFoundException("Un membre doit d'abord être inscrit avant de ce reinscrire");
        });

        if (this.reRegistrationRepository.findById(memberAndYearKey).isPresent()) {
            throw new UserAlreadyExistsException("Ce membre est déja inscrit pour l'année " + memberAndYearKey.getYearOfRegistration());
        }
        MemberHasBourseKey memberHasBourseKey = this.memberHasBourseKeyMapper.toEntity(reRegistrationDto.getMemberHasBourseKeyDto());
        if (!this.memberHasBourseRepository.existsById(memberHasBourseKey)){
            MemberHasBourse memberHasBourse = new MemberHasBourse();
            memberHasBourse.setMemberHasBourseKey(memberHasBourseKey);
            this.memberHasBourseRepository.save(memberHasBourse);
        }
        reRegistration = this.reRegistrationRepository.save(reRegistration);
        RegistrationResponseDto registrationResponseDto = new RegistrationResponseDto();
        registrationResponseDto.setMemberDto(this.memberMapper.toDto(reRegistration.getMember()));
       // registrationResponseDto.setYear(reRegistration.getMember().getYearOfMembership());
        ResponseVO<RegistrationResponseDto> registrationResponseDtoResponseVO = new ResponseVOBuilder<RegistrationResponseDto>().addData(registrationResponseDto).build();
        return new ResponseEntity<>(registrationResponseDtoResponseVO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseVO<RegistrationPearYearDto>> getAllReRegisterForAYear(Long yearId) {
       // List<Member> member = this.reRegistrationRepository.findAllByYearOfReRegistration(yearId).orElseThrow(() -> new RuntimeException("Pas de member inscript pour cette années"));
        RegistrationPearYearDto registrationPearYearDto = new RegistrationPearYearDto();
        List<MemberRequestDto> memberRequestDtos = new ArrayList<>();
        registrationPearYearDto.setYear(yearId);
        /*
        member.forEach(member1 -> {
            memberRequestDtos.add(this.memberMapper.toDto(member1));
        });

         */
        registrationPearYearDto.setRegisterMembers(memberRequestDtos);
        ResponseVO<RegistrationPearYearDto> registrationPearYearDtoResponseVO = new ResponseVOBuilder<RegistrationPearYearDto>().addData(registrationPearYearDto).build();
        return new ResponseEntity<>(registrationPearYearDtoResponseVO, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<ResponseVO<RegistrationPearYearDto>> getAllNotReRegisterForAYear(Long yearId) {
        //List<Member> membersNotRegisterForAYear = this.reRegistrationRepository.findMembersNotRegisteredByYear(yearId).orElseThrow(() -> new RuntimeException("Tous les membre se sont inscrit pour cette année" + yearId));
        RegistrationPearYearDto notRegisterForYear;
        notRegisterForYear = new RegistrationPearYearDto();
       // List<MemberRequestDto> memberRequestDtos = new ArrayList<>(this.memberMapper.toDto(membersNotRegisterForAYear));
        notRegisterForYear.setYear(yearId);
      //  notRegisterForYear.setRegisterMembers(memberRequestDtos);
        return new ResponseEntity<>(new ResponseVOBuilder<RegistrationPearYearDto>().addData(notRegisterForYear).build(), HttpStatus.FOUND);
    }
}
