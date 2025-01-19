package org.aemudapi.member.mapper;

import lombok.AllArgsConstructor;
import org.aemudapi.member.dtos.MemberDataResponseDTO;
import org.aemudapi.member.dtos.MemberRequestDto;
import org.aemudapi.member.dtos.MemberResponseDto;
import org.aemudapi.member.entity.Member;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class MemberMapper {
    private final PersonalInfoMapper personalInfoMapper;
    private final MembershipInfoMapper membershipInfo;
    private final MemberResponseMapper memberResponseMapper;
    private final AcademicInfoMapper academicInfoMapper;
    private final AddressInfoMapper addressInfoMapper;
    private final BourseMapper bourseMapper;
    private final ContactInfoMapper contactInfoMapper;
    private final ClubMapper clubMapper;
    private final CommissionMapper commissionMapper;


    public MemberDataResponseDTO toDto(Member member) {
        if (member == null) {
            return null;
        }
        MemberDataResponseDTO dto = new MemberDataResponseDTO();
        dto.setId(member.getId());
        dto.setPersonalInfo(this.personalInfoMapper.toDto(member.getPersonalInfo()));
        dto.setPersonalInfo(this.personalInfoMapper.toDto(member.getPersonalInfo()));
        dto.setMembershipInfo(this.membershipInfo.toDto(member.getMembershipInfo()));
        dto.setAcademicInfoRequest(academicInfoMapper.toDto(member.getAcademicInfo()));
        dto.setAddressInfo(this.addressInfoMapper.toDto(member.getAddressInfo()));
        dto.setContactInfo(this.contactInfoMapper.toDTO(member.getContactInfo()));
        dto.setBourse(this.bourseMapper.toDTO(member.getBourse()));
        dto.setClubs(this.clubMapper.toDto(member.getClubs()));
        dto.setCommissions(this.commissionMapper.toDto(member.getCommissions()));
        return dto;
    }

    public MemberDataResponseDTO memberDataDto(Member member) {
        if (member == null) {
            return null;
        }
        MemberDataResponseDTO dto = new MemberDataResponseDTO();
        return dto;
    }

    public Member toEntity(MemberRequestDto dto) {
        if (dto == null) {
            return null;
        }
        Member entity = new Member();
        entity.setPersonalInfo(this.personalInfoMapper.toEntity(dto.getPersonalInfo()));
        entity.setMembershipInfo(this.membershipInfo.toEntity(dto.getMembershipInfo()));
        entity.setAcademicInfo(this.academicInfoMapper.toEntity(dto.getAcademicInfoRequest()));
        entity.setAddressInfo(this.addressInfoMapper.toEntity(dto.getAddressInfo()));
        entity.setContactInfo(this.contactInfoMapper.toEntity(dto.getContactInfo()));
        entity.setBourse(this.bourseMapper.toEntity(dto.getBourseId()));
        entity.setClubs(this.clubMapper.toEntity(dto.getClubsId()));
        entity.setCommissions(this.commissionMapper.toEntity(dto.getCommissionsId()));
        return entity;
    }

    public List<MemberDataResponseDTO> toDto(List<Member> members) {
        if (members == null) {
            return null;
        }

        List<MemberDataResponseDTO> dtos = new ArrayList<>();
        for (Member member : members) {
            dtos.add(toDto(member));
        }

        return dtos;
    }

    public List<Member> toEntity(List<MemberRequestDto> dtos) {
        if (dtos == null) {
            return null;
        }

        List<Member> members = new ArrayList<>();
        for (MemberRequestDto dto : dtos) {
            members.add(toEntity(dto));
        }

        return members;
    }


}

