package com.amud.io.aemudapi.mappers;

import com.amud.io.aemudapi.dto.ReRegistrationDto;
import com.amud.io.aemudapi.dto.ReRegistrationKeyDto;
import com.amud.io.aemudapi.entities.MemberAndYearKey;
import com.amud.io.aemudapi.entities.ReRegistration;
import org.springframework.stereotype.Component;

@Component
public class ReRegistrationMapper {
    public ReRegistrationDto toDto(ReRegistration reRegistration) {
        if (reRegistration == null) {
            return null;
        }

        ReRegistrationKeyDto keyDto = new ReRegistrationKeyDto();
        keyDto.setYearOfRegistration(reRegistration.getMemberAndYearKey().getYearOfRegistration());
        keyDto.setMemberId(reRegistration.getMemberAndYearKey().getMember());

        ReRegistrationDto dto = new ReRegistrationDto();
        dto.setReRegistrationKeyDto(keyDto);

        return dto;
    }

    public ReRegistration toEntity(ReRegistrationDto dto) {
        if (dto == null) {
            return null;
        }

        MemberAndYearKey key = new MemberAndYearKey();
        key.setYearOfRegistration(dto.getReRegistrationKeyDto().getYearOfRegistration());
        key.setMember(dto.getReRegistrationKeyDto().getMemberId());

        ReRegistration reRegistration = new ReRegistration();
        reRegistration.setMemberAndYearKey(key);

        return reRegistration;
    }
}
