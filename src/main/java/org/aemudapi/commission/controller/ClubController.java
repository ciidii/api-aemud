package org.aemudapi.commission.controller;

import org.aemudapi.member.dtos.ClubDto;
import org.aemudapi.member.service.ClubService;
import org.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "clubs")
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

    @DeleteMapping()
    ResponseEntity<ResponseVO<Void>> deleteClub(@RequestParam("clubId") Long clubId) {
        return this.clubService.deleteClub(clubId);
    }

    @GetMapping
    ResponseEntity<ResponseVO<ClubDto>> getClubById(@RequestParam("clubId") Long clubId) {
        return this.clubService.getClubById(clubId);
    }
}
