package com.amud.io.aemudapi.services.impl;

import com.amud.io.aemudapi.dto.ClubDto;
import com.amud.io.aemudapi.dto.FilterDTO;
import com.amud.io.aemudapi.entities.Club;
import com.amud.io.aemudapi.entities.Member;
import com.amud.io.aemudapi.exceptions.customeExceptions.EntityCannotBeDeletedException;
import com.amud.io.aemudapi.mappers.ClubMapper;
import com.amud.io.aemudapi.repositories.ClubRepository;
import com.amud.io.aemudapi.repositories.MemberRepository;
import com.amud.io.aemudapi.services.ClubService;
import com.amud.io.aemudapi.services.MemberService;
import com.amud.io.aemudapi.utils.ResponseVO;
import com.amud.io.aemudapi.utils.ResponseVOBuilder;
import com.amud.io.aemudapi.utils.Utils;
import jakarta.persistence.EntityNotFoundException;
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
    public ResponseEntity<ResponseVO<Void>> deleteClub(Long id) {
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
    public ResponseEntity<ResponseVO<ClubDto>> getClubById(Long clubId) {
        Club clubFromDb = this.clubRepository.findById(clubId).orElseThrow(() -> new EntityNotFoundException("Club with " + clubId + " not found"));
        ResponseVO<ClubDto> responseVO = new ResponseVOBuilder<ClubDto>().addData(this.clubMapper.toDto(clubFromDb)).build();
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }

}
