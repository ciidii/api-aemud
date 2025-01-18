package org.aemudapi.member.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.aemudapi.exceptions.customeExceptions.EntityCannotBeDeletedException;
import org.aemudapi.exceptions.customeExceptions.NoActiveSection;
import org.aemudapi.member.dtos.SessionResponseDTO;
import org.aemudapi.member.entity.Session;
import org.aemudapi.member.mapper.SessionMapper;
import org.aemudapi.member.repository.MemberRepository;
import org.aemudapi.member.repository.YearOfSessionRepository;
import org.aemudapi.member.service.SessionService;
import org.aemudapi.utils.ResponseVO;
import org.aemudapi.utils.ResponseVOBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {
    private final YearOfSessionRepository yearOfSessionRepository;
    private final SessionMapper sessionMapper;
    private final MemberRepository memberRepository;

    public SessionServiceImpl(YearOfSessionRepository yearOfSessionServices, SessionMapper sessionMapper, MemberRepository memberRepository) {
        this.yearOfSessionRepository = yearOfSessionServices;
        this.sessionMapper = sessionMapper;
        this.memberRepository = memberRepository;
    }


    @Override
    public ResponseEntity<ResponseVO<Void>> openNewSession(int year_) {
        org.aemudapi.member.entity.Session session = new org.aemudapi.member.entity.Session();
        session.setYear_(year_);
        session.setCurrent(true);
        this.yearOfSessionRepository.updateCurrentYear();
        this.yearOfSessionRepository.save(session);
        return new ResponseEntity<>(new ResponseVOBuilder<Void>().success().build(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseVO<List<SessionResponseDTO>>> getAllSessions() {
        List<SessionResponseDTO> sessionResponsDTOS = new ArrayList<>();
        this.yearOfSessionRepository.findAll().forEach(yearOfSession -> {
            sessionResponsDTOS.add(this.sessionMapper.toDto(yearOfSession));
        });
        ResponseVO<List<SessionResponseDTO>> responseVO = new ResponseVOBuilder<List<SessionResponseDTO>>().addData(sessionResponsDTOS).build();
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<SessionResponseDTO>> getCurrentSession() {
        org.aemudapi.member.entity.Session currentSession = this.yearOfSessionRepository.findCurrentSession().orElseThrow(() -> new NoActiveSection("No active session"));
        SessionResponseDTO sessionResponseDTO = this.sessionMapper.toDto(currentSession);
        ResponseVO<SessionResponseDTO> responseVO = new ResponseVOBuilder<SessionResponseDTO>().addData(sessionResponseDTO).build();

        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<SessionResponseDTO>> getSession(String sessionid) {
        org.aemudapi.member.entity.Session session = this.yearOfSessionRepository.findById(sessionid).orElseThrow(() -> new EntityNotFoundException("No session with id=" + sessionid));
        SessionResponseDTO sessionResponseDTO = this.sessionMapper.toDto(session);
        ResponseVO<SessionResponseDTO> responseVO = new ResponseVOBuilder<SessionResponseDTO>().addData(sessionResponseDTO).build();

        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<Void>> deleteSession(String sessionid) {
        Session session = this.yearOfSessionRepository.findById(sessionid).orElseThrow(() -> new EntityNotFoundException("No active session"));

        throw new EntityCannotBeDeletedException("La fonction n'est encors implementer");
    }
}
