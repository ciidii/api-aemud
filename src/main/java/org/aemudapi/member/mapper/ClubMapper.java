package org.aemudapi.member.mapper;

import org.aemudapi.club.entity.Club;
import org.aemudapi.member.dtos.ClubDto;
import org.aemudapi.member.dtos.ClubIdDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    public Club toEntity(ClubIdDto clubId) {
        Club club = new Club();
        club.setId(clubId.getId());
        return club;
    }

    public List<ClubDto> toDto(List<Club> clubs) {
        List<ClubDto> dtos = new ArrayList<>();
        for (Club club : clubs) {
            dtos.add(this.toDto(club));
        }
        return dtos;
    }


    public List<Club> toEntity(List<ClubIdDto> clubIDs) {
        List<Club> clubs = new ArrayList<>();
        for (ClubIdDto dto : clubIDs) {
            clubs.add(this.toEntity(dto));
        }
        return clubs;
    }
}
