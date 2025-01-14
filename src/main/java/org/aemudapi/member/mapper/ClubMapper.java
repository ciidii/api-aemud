package org.aemudapi.member.mapper;

import org.aemudapi.member.dtos.ClubDto;
import org.aemudapi.member.entity.Club;
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
