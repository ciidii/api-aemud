package com.amud.io.aemudapi.services;

import com.amud.io.aemudapi.dto.YearOfSessionResponse;
import com.amud.io.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface YearOfSessionServices {
    ResponseEntity<ResponseVO<Void>> openNewSession(int year_);

    ResponseEntity<ResponseVO<List<YearOfSessionResponse>>> getAllYears();

    ResponseEntity<ResponseVO<YearOfSessionResponse>> getCurrentSession();
}
