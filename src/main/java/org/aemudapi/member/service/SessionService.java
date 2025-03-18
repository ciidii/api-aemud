package org.aemudapi.member.service;

import org.aemudapi.member.dtos.SessionRequestDTO;
import org.aemudapi.member.dtos.SessionResponseDTO;
import org.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SessionService {
    ResponseEntity<ResponseVO<Void>> openNewSession(SessionRequestDTO session);

    ResponseEntity<ResponseVO<List<SessionResponseDTO>>> getAllSessions();

    ResponseEntity<ResponseVO<SessionResponseDTO>> getCurrentSession();

    ResponseEntity<ResponseVO<SessionResponseDTO>> getSession(String sessionid);

    ResponseEntity<ResponseVO<Void>> deleteSession(String sessionid);
}
