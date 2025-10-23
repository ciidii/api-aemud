package org.aemudapi.member.mapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.aemudapi.mandat.repository.MandatRepository;
import org.aemudapi.member.dtos.AddressInfoRequestDto;
import org.aemudapi.member.entity.AddressInfo;
import org.aemudapi.member.entity.Member;
import org.aemudapi.member.repository.MemberRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AddressInfoMapper {
    private final MemberRepository memberRepository;
    private final MandatRepository mandatRepository;

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


}
