package com.amud.io.aemudapi.controllers;

import com.amud.io.aemudapi.dto.ClubDto;
import com.amud.io.aemudapi.services.ClubService;
import com.amud.io.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "clubs")
@CrossOrigin(origins = "http://localhost:4200")
public class ClubController {
    private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @PostMapping
    ResponseEntity<ResponseVO<ClubDto>> addClubs(@RequestBody ClubDto clubDto) {
        return this.clubService.addClub(clubDto);
    }

    @GetMapping(path = "all")
    ResponseEntity<ResponseVO<List<ClubDto>>> getClubs() {
        return this.clubService.getAllClubs();
    }

}
