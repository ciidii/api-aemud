package com.amud.io.aemudapi.services;

import com.amud.io.aemudapi.dto.ClubDto;
import com.amud.io.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClubService {
    ResponseEntity<ResponseVO<ClubDto>> addClub(ClubDto clubDto);

    ResponseEntity<ResponseVO<List<ClubDto>>> getAllClubs();

    ResponseEntity<ResponseVO<Void>> deleteClub(Long id);

    ResponseEntity<ResponseVO<ClubDto>> getClubById(Long clubId);
}
