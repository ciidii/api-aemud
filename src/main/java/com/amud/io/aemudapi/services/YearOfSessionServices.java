package com.amud.io.aemudapi.services;

import com.amud.io.aemudapi.dto.YearOfSessionResponse;
import com.amud.io.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface YearOfSessionServices {
    public ResponseEntity<ResponseVO<Void>> openNewSession(int year_);
    public ResponseEntity<ResponseVO<List<YearOfSessionResponse>>> getAllYears();
    public ResponseEntity<ResponseVO<YearOfSessionResponse>> getCurrentSession();
}
