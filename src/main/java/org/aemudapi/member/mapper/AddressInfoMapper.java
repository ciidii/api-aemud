package org.aemudapi.member.mapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.aemudapi.member.dtos.AddressInfoRequestDto;
import org.aemudapi.member.entity.MemberAndYearKey;
import org.aemudapi.member.entity.YearOfSession;
import org.aemudapi.member.entity.AddressInfo;
import org.aemudapi.member.entity.Member;
import org.aemudapi.member.repository.MemberRepository;
import org.aemudapi.member.repository.YearOfSessionRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AddressInfoMapper {
    private final MemberRepository memberRepository;
    private final YearOfSessionRepository yearOfSessionRepository;

    public AddressInfo toEntity(AddressInfoRequestDto dto) {
        if (dto == null) {
            return null;
        }

        AddressInfo addressInfo = new AddressInfo();
        MemberAndYearKey memberAndYearKey = new MemberAndYearKey();
        memberAndYearKey.setYearOfRegistration(getIdYear(dto.getIdYear()));
        memberAndYearKey.setMember(getMemberID(dto.getMemberID()));

        addressInfo.setAddressInDakar(dto.getAddressInDakar());
        addressInfo.setHolidayAddress(dto.getHolidayAddress());
        addressInfo.setAddressToCampus(dto.getAddressToCampus());
        addressInfo.setMemberAndYearKey(memberAndYearKey);
        return addressInfo;
    }

    public AddressInfoRequestDto toDto(AddressInfo addressInfo) {
        if (addressInfo == null) {
            return null;
        }

        AddressInfoRequestDto dto = new AddressInfoRequestDto();
        dto.setMemberID(addressInfo.getMember().getId());
        dto.setIdYear(addressInfo.getMemberAndYearKey().getYearOfRegistration());
        dto.setAddressInDakar(addressInfo.getAddressInDakar());
        dto.setHolidayAddress(addressInfo.getHolidayAddress());
        dto.setAddressToCampus(addressInfo.getAddressToCampus());

        return dto;
    }

    private Long getMemberID(Long idMember) {
        Member member = this.memberRepository.findById(idMember)
                .orElseThrow(() -> new EntityNotFoundException("Pas de membre avec cette Identifiant" + idMember));
        return member.getId();
    }

    private Long getIdYear(Long idYear) {
        YearOfSession yearOfSession = this.yearOfSessionRepository.findById(idYear)
                .orElseThrow(() -> new EntityNotFoundException("Pas d'année avec cette identifiant" + idYear));
        return yearOfSession.getIdYear();
    }
}