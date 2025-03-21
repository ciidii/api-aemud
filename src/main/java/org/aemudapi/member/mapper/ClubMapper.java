package org.aemudapi.member.mapper;

import lombok.RequiredArgsConstructor;
import org.aemudapi.club.entity.Club;
import org.aemudapi.member.dtos.ClubDto;
import org.aemudapi.member.dtos.ClubIdDto;
import org.aemudapi.member.entity.Member;
import org.aemudapi.member.repository.MemberRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ClubMapper {
    private final MemberRepository memberRepository;
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
        List<Member> member =  this.memberRepository.findAllById(clubDto.getMembers());
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
