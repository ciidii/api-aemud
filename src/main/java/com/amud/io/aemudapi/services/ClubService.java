package com.amud.io.aemudapi.services;

import com.amud.io.aemudapi.dto.ClubDto;
import com.amud.io.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClubService {
    public ResponseEntity<ResponseVO<ClubDto>> addClub(ClubDto clubDto);
    public ResponseEntity<ResponseVO<List<ClubDto>>> getAllClubs();
}
