package com.amud.io.aemudapi.mappers;

import com.amud.io.aemudapi.dto.ClubDto;
import com.amud.io.aemudapi.entities.Club;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ClubMapper {
    public ClubDto toDto(Club club) {
        ClubDto dto = new ClubDto();
        dto.setId(club.getId());
        dto.setName(club.getName());
        return dto;
    }

    public Club toEntity(ClubDto clubDto) {
        Club club = new Club();
        club.setId(clubDto.getId());
        club.setName(clubDto.getName());
        return club;
    }
}
