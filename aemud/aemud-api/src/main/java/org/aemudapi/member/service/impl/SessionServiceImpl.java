package org.aemudapi.member.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.aemudapi.exceptions.customeExceptions.CantDeletActiveSectionException;
import org.aemudapi.member.dtos.SessionRequestDTO;
import org.aemudapi.member.dtos.SessionResponseDTO;
import org.aemudapi.member.entity.Session;
import org.aemudapi.member.mapper.SessionMapper;
import org.aemudapi.member.repository.MemberRepository;
import org.aemudapi.member.repository.SessionRepository;
import org.aemudapi.member.service.SessionService;
import org.aemudapi.utils.ResponseVO;
import org.aemudapi.utils.ResponseVOBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;
    private final SessionMapper sessionMapper;

    @Override
    public ResponseEntity<ResponseVO<Void>> openNewSession(SessionRequestDTO sessionRequestDTO) {
        Session session = this.sessionMapper.toEntity(sessionRequestDTO);
        if (sessionRequestDTO.isCurrentYear()) {
            this.sessionRepository.updateCurrentYear();
        }
        this.sessionRepository.save(session);
        return new ResponseEntity<>(new ResponseVOBuilder<Void>().success().build(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseVO<List<SessionResponseDTO>>> getAllSessions() {
        List<SessionResponseDTO> sessionResponsDTOS = new ArrayList<>();
        this.sessionRepository.findAll().forEach(yearOfSession -> {
            sessionResponsDTOS.add(this.sessionMapper.toDto(yearOfSession));
        });
        ResponseVO<List<SessionResponseDTO>> responseVO = new ResponseVOBuilder<List<SessionResponseDTO>>().addData(sessionResponsDTOS).build();
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<SessionResponseDTO>> getCurrentSession() {
        org.aemudapi.member.entity.Session currentSession = this.sessionRepository.findCurrentSession().orElseThrow(() -> new CantDeletActiveSectionException("No active session"));
        SessionResponseDTO sessionResponseDTO = this.sessionMapper.toDto(currentSession);
        ResponseVO<SessionResponseDTO> responseVO = new ResponseVOBuilder<SessionResponseDTO>().addData(sessionResponseDTO).build();

        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<SessionResponseDTO>> getSession(String sessionid) {
        org.aemudapi.member.entity.Session session = this.sessionRepository.findById(sessionid).orElseThrow(() -> new EntityNotFoundException("No session with id=" + sessionid));
        SessionResponseDTO sessionResponseDTO = this.sessionMapper.toDto(session);
        ResponseVO<SessionResponseDTO> responseVO = new ResponseVOBuilder<SessionResponseDTO>().addData(sessionResponseDTO).build();

        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<Void>> deleteSession(String sessionid) {
        Session session = this.sessionRepository.findById(sessionid).orElseThrow(() -> new EntityNotFoundException("No session"));
        if (session.isCurrent()) {
            throw new CantDeletActiveSectionException("Active can't be deleted");
        }
        this.sessionRepository.delete(session);
        return new ResponseEntity<>(new ResponseVOBuilder<Void>().success().build(), HttpStatus.OK);
    }
}
