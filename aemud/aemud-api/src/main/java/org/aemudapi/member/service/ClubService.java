package org.aemudapi.member.service;

import org.aemudapi.member.dtos.ClubDto;
import org.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClubService {
    ResponseEntity<ResponseVO<ClubDto>> addClub(ClubDto clubDto);

    ResponseEntity<ResponseVO<List<ClubDto>>> getAllClubs();

    ResponseEntity<ResponseVO<Void>> deleteClub(String id);

    ResponseEntity<ResponseVO<ClubDto>> getClubById(String clubId);
}
