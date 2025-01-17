package org.aemudapi.member.service;

import org.aemudapi.member.dtos.YearOfSessionResponse;
import org.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Session {
    ResponseEntity<ResponseVO<Void>> openNewSession(int year_);

    ResponseEntity<ResponseVO<List<YearOfSessionResponse>>> getAllYears();

    ResponseEntity<ResponseVO<YearOfSessionResponse>> getCurrentSession();

    ResponseEntity<ResponseVO<YearOfSessionResponse>> getAParticularYear(Long sessionid);

    ResponseEntity<ResponseVO<Boolean>> checkIfCanDeleteSession(Long sessionid);

    ResponseEntity<ResponseVO<Void>> deleteSession(Long sessionid);
}
