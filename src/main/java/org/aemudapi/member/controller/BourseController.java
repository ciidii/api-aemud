package org.aemudapi.member.controller;

import org.aemudapi.member.dtos.BourseDTO;
import org.aemudapi.member.service.BourseService;
import org.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bourses")
public class BourseController {
    private final BourseService bourseService;

    public BourseController(BourseService bourseService) {
        this.bourseService = bourseService;
    }

    @PostMapping()
    ResponseEntity<ResponseVO<BourseDTO>> addBourse(@RequestBody BourseDTO bourseDTO) {
        return this.bourseService.addBourse(bourseDTO);
    }

    @GetMapping("all")
    ResponseEntity<ResponseVO<List<BourseDTO>>> findAllBourse() {
        return this.bourseService.getAllBourse();
    }

    @GetMapping("member-Contribution-amount")
    ResponseEntity<ResponseVO<Double>> getMemberOfContributionAmount(@RequestParam("numberPhone") String numberPhone) {

        return this.bourseService.getMemberOfContributionAmount(numberPhone.trim());
    }

    @PutMapping()
    ResponseEntity<ResponseVO<BourseDTO>> updateBourse(@RequestBody BourseDTO bourseDTO) {
        return this.bourseService.addBourse(bourseDTO);
    }

    @DeleteMapping
    ResponseEntity<ResponseVO<Void>> deleteBourse(@RequestParam("bourseId") String bourseId) {
        return this.bourseService.deleteBourse(bourseId);
    }

    @GetMapping()
    ResponseEntity<ResponseVO<BourseDTO>> findBourseById(@RequestParam("bourseId") String bourseId) {
        return this.bourseService.findBourseById(bourseId);
    }
}
