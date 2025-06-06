package org.aemudapi.member.mapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.aemudapi.member.dtos.AddressInfoRequestDto;
import org.aemudapi.member.entity.Session;
import org.aemudapi.member.entity.AddressInfo;
import org.aemudapi.member.entity.Member;
import org.aemudapi.member.repository.MemberRepository;
import org.aemudapi.member.repository.SessionRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AddressInfoMapper {
    private final MemberRepository memberRepository;
    private final SessionRepository sessionRepository;

    public AddressInfo toEntity(AddressInfoRequestDto dto) {
        if (dto == null) {
            return null;
        }

        AddressInfo addressInfo = new AddressInfo();

        addressInfo.setAddressInDakar(dto.getAddressInDakar());
        addressInfo.setHolidayAddress(dto.getHolidayAddress());
        addressInfo.setAddressToCampus(dto.getAddressToCampus());
        return addressInfo;
    }

    public AddressInfoRequestDto toDto(AddressInfo addressInfo) {
        if (addressInfo == null) {
            return null;
        }

        AddressInfoRequestDto dto = new AddressInfoRequestDto();
       // dto.setMemberID(addressInfo.getMember().getId());
       // dto.setIdYear(addressInfo.getMemberSessionPK().getSessionID());
        dto.setAddressInDakar(addressInfo.getAddressInDakar());
        dto.setHolidayAddress(addressInfo.getHolidayAddress());
        dto.setAddressToCampus(addressInfo.getAddressToCampus());

        return dto;
    }

    private String getMemberID(String idMember) {
        Member member = this.memberRepository.findById(idMember)
                .orElseThrow(() -> new EntityNotFoundException("Pas de membre avec cette Identifiant" + idMember));
        return member.getId();
    }

    private String getIdYear(String idYear) {
        Session session = this.sessionRepository.findById(idYear)
                .orElseThrow(() -> new EntityNotFoundException("Pas d'année avec cette identifiant" + idYear));
        return session.getId();
    }
}
