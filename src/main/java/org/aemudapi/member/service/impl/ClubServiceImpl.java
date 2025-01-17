package org.aemudapi.member.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.aemudapi.member.dtos.ClubDto;
import org.aemudapi.member.dtos.FilterDTO;
import org.aemudapi.exceptions.customeExceptions.EntityCannotBeDeletedException;
import org.aemudapi.club.entity.Club;
import org.aemudapi.member.entity.Member;
import org.aemudapi.member.mapper.ClubMapper;
import org.aemudapi.club.repository.ClubRepository;
import org.aemudapi.member.repository.MemberRepository;
import org.aemudapi.member.service.ClubService;
import org.aemudapi.utils.ResponseVO;
import org.aemudapi.utils.ResponseVOBuilder;
import org.aemudapi.utils.Utils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClubServiceImpl implements ClubService {
    private final ClubRepository clubRepository;
    private final ClubMapper clubMapper;
    private final MemberRepository memberRepository;

    public ClubServiceImpl(ClubRepository clubRepository, ClubMapper clubMapper, MemberRepository memberRepository) {
        this.clubRepository = clubRepository;
        this.clubMapper = clubMapper;
        this.memberRepository = memberRepository;
    }

    @Override
    public ResponseEntity<ResponseVO<ClubDto>> addClub(ClubDto clubDto) {
        Club club = this.clubMapper.toEntity(clubDto);
        club = this.clubRepository.save(club);
        clubDto = this.clubMapper.toDto(club);
        ResponseVO<ClubDto> responseVO = new ResponseVOBuilder<ClubDto>().addData(clubDto).build();
        return new ResponseEntity<>(responseVO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseVO<List<ClubDto>>> getAllClubs() {
        List<ClubDto> clubsDto = new ArrayList<>();
        this.clubRepository.findAll().forEach(club -> {
            clubsDto.add(this.clubMapper.toDto(club));
        });
        ResponseVO<List<ClubDto>> responseVO = new ResponseVOBuilder<List<ClubDto>>().addData(clubsDto).build();
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<Void>> deleteClub(String id) {
        FilterDTO filterDTO = new FilterDTO(id, null, null);
        Specification<Member> memberSpecification = Utils.makeFilterCriteriaSpec(null, null, filterDTO);
        List<Member> members = memberRepository.findAll(memberSpecification);
        if (members.isEmpty()) {
            this.clubRepository.deleteById(id);
            return new ResponseEntity<>(new ResponseVOBuilder<Void>().success().build(), HttpStatus.OK);
        }
        throw new EntityCannotBeDeletedException("Impossible to delete Club");
    }

    @Override
    public ResponseEntity<ResponseVO<ClubDto>> getClubById(String clubId) {
        Club clubFromDb = this.clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("Club with " + clubId + " not found"));
        ResponseVO<ClubDto> responseVO = new ResponseVOBuilder<ClubDto>().addData(this.clubMapper.toDto(clubFromDb)).build();
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }

}
