package org.aemudapi.member.controller;

import lombok.AllArgsConstructor;
import org.aemudapi.member.dtos.SessionRequestDTO;
import org.aemudapi.member.dtos.SessionResponseDTO;
import org.aemudapi.member.service.SessionService;
import org.aemudapi.utils.ResponseVO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("session")
@AllArgsConstructor
public class SessionController {
    private final SessionService session;


    @PostMapping
    ResponseEntity<ResponseVO<Void>> openNewSession(@RequestBody SessionRequestDTO year_) {
        return this.session.openNewSession(year_.getYear_());
    }

    @GetMapping("current")
    public ResponseEntity<ResponseVO<SessionResponseDTO>> getCurrentSession() {
        return this.session.getCurrentSession();
    }

    @GetMapping("all")
    public ResponseEntity<ResponseVO<List<SessionResponseDTO>>> getAllCurrent() {
        return this.session.getAllSessions();
    }

    @GetMapping()
    public ResponseEntity<ResponseVO<SessionResponseDTO>> getAParticularYear(@RequestParam("sessionid") String sessionid) {
        return this.session.getSession(sessionid);
    }

    @DeleteMapping()
    public ResponseEntity<ResponseVO<Void>> deleteSession(@RequestParam("sessionid") String sessionid) {
        return this.session.deleteSession(sessionid);
    }
}
