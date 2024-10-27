package com.amud.io.aemudapi.services.impl;

import com.amud.io.aemudapi.dto.ClubDto;
import com.amud.io.aemudapi.entities.Club;
import com.amud.io.aemudapi.mappers.ClubMapper;
import com.amud.io.aemudapi.repositories.ClubRepository;
import com.amud.io.aemudapi.services.ClubService;
import com.amud.io.aemudapi.utils.ResponseVO;
import com.amud.io.aemudapi.utils.ResponseVOBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClubServiceImpl implements ClubService {
    private final ClubRepository clubRepository;
    private final ClubMapper clubMapper;

    public ClubServiceImpl(ClubRepository clubRepository, ClubMapper clubMapper) {
        this.clubRepository = clubRepository;
        this.clubMapper = clubMapper;
    }

    @Override
    public ResponseEntity<ResponseVO<ClubDto>> addClub(ClubDto clubDto) {
        Club club = this.clubMapper.toEntity(clubDto);
        club =  this.clubRepository.save(club);
        clubDto = this.clubMapper.toDto(club);
         ResponseVO<ClubDto> responseVO = new ResponseVOBuilder<ClubDto>().addData(clubDto).build();
        return new ResponseEntity<>(responseVO,HttpStatus.CREATED);
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

}
