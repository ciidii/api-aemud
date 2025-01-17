package org.aemudapi.member.controller;

import org.aemudapi.member.dtos.SessionRequestDTO;
import org.aemudapi.member.dtos.YearOfSessionResponse;
import org.aemudapi.member.service.Session;
import org.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("session")
public class YearOfSessionController {
    private final Session session;

    public YearOfSessionController(Session session) {
        this.session = session;
    }

    @PostMapping
    ResponseEntity<ResponseVO<Void>> openNewSession(@RequestBody SessionRequestDTO year_) {
        return this.session.openNewSession(year_.getYear_());
    }

    @GetMapping("current")
    public ResponseEntity<ResponseVO<YearOfSessionResponse>> getCurrentSession() {
        return this.session.getCurrentSession();
    }

    @GetMapping("all")
    public ResponseEntity<ResponseVO<List<YearOfSessionResponse>>> getAllCurrent() {
        return this.session.getAllYears();
    }

    @GetMapping()
    public ResponseEntity<ResponseVO<YearOfSessionResponse>> getAParticularYear(@RequestParam("sessionid") Long sessionid) {
        return this.session.getAParticularYear(sessionid);
    }

    @GetMapping("check")
    public ResponseEntity<ResponseVO<Boolean>> checkIfCanDeleteSession(@RequestParam("sessionid") Long sessionid) {
        return this.session.checkIfCanDeleteSession(sessionid);
    }

    @DeleteMapping()
    public ResponseEntity<ResponseVO<Void>> deleteSession(@RequestParam("sessionid") Long sessionid) {
        return this.session.deleteSession(sessionid);
    }
}
