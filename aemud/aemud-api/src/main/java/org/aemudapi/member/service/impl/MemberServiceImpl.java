package org.aemudapi.member.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.aemudapi.exceptions.customeExceptions.ActiveMandateNotFoundException;
import org.aemudapi.exceptions.customeExceptions.UserAlreadyExistsException;
import org.aemudapi.mandat.entity.Mandat;
import org.aemudapi.mandat.repository.MandatRepository;
import org.aemudapi.member.dtos.FilterDTO;
import org.aemudapi.member.dtos.MemberDataResponseDTO;
import org.aemudapi.member.dtos.MemberRequestDto;
import org.aemudapi.member.dtos.RegistrationRequestDto;
import org.aemudapi.member.entity.Member;
import org.aemudapi.member.entity.RegistrationStatus;
import org.aemudapi.member.entity.TypeInscription;
import org.aemudapi.member.mapper.MemberMapper;
import org.aemudapi.member.repository.MemberRepository;
import org.aemudapi.member.service.MemberService;
import org.aemudapi.member.service.RegistrationService;
import org.aemudapi.utils.RequestPageableVO;
import org.aemudapi.utils.ResponsePageableVO;
import org.aemudapi.utils.ResponseVO;
import org.aemudapi.utils.ResponseVOBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.aemudapi.utils.Utils.makeFilterCriteriaSpec;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final RegistrationService registrationService;
    private final MandatRepository mandatRepository;
    
    @Override
    @Transactional
    public ResponseEntity<ResponseVO<MemberDataResponseDTO>> addMember(MemberRequestDto memberRequestDto) {
        // 1. Valider l'unicité du membre par numéro de téléphone
        String phoneNumber = memberRequestDto.getContactInfo().getNumberPhone();
        if (this.memberRepository.findByContactInfoNumberPhone(phoneNumber).isPresent()) {
            throw new UserAlreadyExistsException("Un membre avec le numéro de téléphone '" + phoneNumber + "' existe déjà.");
        }
    
        // 2. Récupérer le mandat actif
        Mandat mandat = this.mandatRepository.findMandatByEstActif(true)
                .orElseThrow(() -> new ActiveMandateNotFoundException("Aucun mandat actif n'a été trouvé. Impossible de créer un nouveau membre."));
    
        // 3. Mapper le DTO en entité
        Member member = this.memberMapper.toEntity(memberRequestDto);
        
        // 4. S'assurer que l'ID est null pour la création
        member.setId(null);
    
        // 5. Sauvegarder le nouveau membre
        Member memberFromDB = this.memberRepository.save(member);
    
        // 6. Gérer l'inscription initiale
        RegistrationRequestDto registrationRequestDto = new RegistrationRequestDto();
        registrationRequestDto.setMember(memberFromDB.getId());
        registrationRequestDto.setRegistrationStatus(RegistrationStatus.UNCOMPLETED);
        registrationRequestDto.setRegistrationType(TypeInscription.INITIAL);
        registrationRequestDto.setMandatId(mandat.getId());
        registrationRequestDto.setStatusPayment(false);
        this.registrationService.registerMember(registrationRequestDto);
    
        // 7. Préparer et retourner la réponse
        MemberDataResponseDTO dto = this.memberMapper.toDto(memberFromDB);
        ResponseVO<MemberDataResponseDTO> responseVO = new ResponseVOBuilder<MemberDataResponseDTO>().addData(dto).build();
        return new ResponseEntity<>(responseVO, HttpStatus.CREATED);
    }
    @Override
    public ResponseEntity<ResponseVO<MemberDataResponseDTO>> updateMember(MemberRequestDto memberRequestDto) {
        Member save = this.memberRepository.save(memberMapper.toEntity(memberRequestDto));
        ResponseVO<MemberDataResponseDTO> responseVO = new ResponseVOBuilder<MemberDataResponseDTO>().addData(this.memberMapper.toDto(save)).build();
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ResponseVO<MemberDataResponseDTO>> getMemberById(String id) {
        return memberRepository.findById(id).map(member -> {
            MemberDataResponseDTO memberRequestDto = memberMapper.toDto(member);
            ResponseVO<MemberDataResponseDTO> responseVO = new ResponseVOBuilder<MemberDataResponseDTO>().addData(memberRequestDto).build();
            return ResponseEntity.status(HttpStatus.OK).body(responseVO);
        }).orElseThrow(() -> new EntityNotFoundException("Un membre avec l'id " + id + " n'existe pas"));
    }

    @Override
    public ResponseEntity<ResponsePageableVO<MemberDataResponseDTO>> searchMember(RequestPageableVO requestPageableVO, String criteria, String value, FilterDTO filterDTO, String sortColumn, boolean sortDirection) {
        Sort sort = sortDirection ? Sort.by(sortColumn).ascending() : Sort.by(sortColumn).descending();
        PageRequest pageRequest = PageRequest.of(requestPageableVO.getPage() - 1, requestPageableVO.getRpp(), sort);
        Specification<Member> memberSpecification = makeFilterCriteriaSpec(criteria, value, filterDTO);
        Page<Member> memberPage = memberRepository.findAll(memberSpecification, pageRequest);
        List<MemberDataResponseDTO> memberDataResponseDTO = this.fetchPageToMemberResponseDTO(memberPage);
        ResponsePageableVO<MemberDataResponseDTO> responsePageableVO = new ResponsePageableVO<>(memberPage.getTotalElements(), memberDataResponseDTO, requestPageableVO);
        return new ResponseEntity<>(responsePageableVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<List<MemberDataResponseDTO>>> searchMemberToPrint(String criteria, String value, FilterDTO filterDTO) {
        Specification<Member> memberSpecification = makeFilterCriteriaSpec(criteria, value, filterDTO);
        List<Member> members = memberRepository.findAll(memberSpecification);
        List<MemberDataResponseDTO> responseDTOS = this.fetchPageToMemberResponseDTO(members);
        ResponseVO<List<MemberDataResponseDTO>> listResponseVO = new ResponseVOBuilder<List<MemberDataResponseDTO>>().addData(responseDTOS).build();
        return new ResponseEntity<>(listResponseVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<MemberDataResponseDTO>> findMemberByNumberPhone(String numberphone) {
        Member member = this.memberRepository.findByNumberPhone(numberphone).orElseThrow(() -> new EntityNotFoundException("Member Introuvable"));
        MemberDataResponseDTO memberDataResponseDTO = memberMapper.toDto(member);

        return new ResponseEntity<>(new ResponseVOBuilder<MemberDataResponseDTO>().addData(memberDataResponseDTO).build(), HttpStatus.OK);
    }


    @Override
    public ResponseEntity removeMember(String id) {
        Member member = this.memberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Un membre avec l'id " + id + " n'existe pas"));
        this.memberRepository.delete(member);
        ResponseVO responseVO = new ResponseVOBuilder<Void>().success().build();
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }

    private <T extends Iterable<Member>> List<MemberDataResponseDTO> fetchPageToMemberResponseDTO(T memberPage) {
        List<MemberDataResponseDTO> memberResponseDtos = new ArrayList<>();
        for (Member member : memberPage) {
            MemberDataResponseDTO memberDataResponseDTO = this.memberMapper.toDto(member);
            memberResponseDtos.add(memberDataResponseDTO);
        }
        return memberResponseDtos;
    }
}
