package com.amud.io.aemudapi.controllers;

import com.amud.io.aemudapi.dto.SessionRequestDTO;
import com.amud.io.aemudapi.dto.YearOfSessionResponse;
import com.amud.io.aemudapi.services.YearOfSessionServices;
import com.amud.io.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("session")
@CrossOrigin(origins = "http://localhost:4200")
public class YearOfSessionController {
    private final YearOfSessionServices yearOfSessionServices;

    public YearOfSessionController(YearOfSessionServices yearOfSessionServices) {
        this.yearOfSessionServices = yearOfSessionServices;
    }

    @PostMapping
    ResponseEntity<ResponseVO<Void>> openNewSession(@RequestBody SessionRequestDTO year_) {
        return this.yearOfSessionServices.openNewSession(year_.getYear_());
    }

    @GetMapping("current")
    public ResponseEntity<ResponseVO<YearOfSessionResponse>> getCurrentSession() {
        return this.yearOfSessionServices.getCurrentSession();
    }

    @GetMapping("all")
    public ResponseEntity<ResponseVO<List<YearOfSessionResponse>>> getAllCurrent() {
        return this.yearOfSessionServices.getAllYears();
    }

    @GetMapping()
    public ResponseEntity<ResponseVO<YearOfSessionResponse>> getAParticularYear(@RequestParam("sessionid") Long sessionid) {
        return this.yearOfSessionServices.getAParticularYear(sessionid);
    }

    @GetMapping("check")
    public ResponseEntity<ResponseVO<Boolean>> checkIfCanDeleteSession(@RequestParam("sessionid") Long sessionid) {
        return this.yearOfSessionServices.checkIfCanDeleteSession(sessionid);
    }
    @DeleteMapping()
    public ResponseEntity<ResponseVO<Void>> deleteSession(@RequestParam("sessionid") Long sessionid) {
        return this.yearOfSessionServices.deleteSession(sessionid);
    }
}
