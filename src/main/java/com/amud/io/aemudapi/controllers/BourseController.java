package com.amud.io.aemudapi.controllers;

import com.amud.io.aemudapi.dto.BourseDTO;
import com.amud.io.aemudapi.services.BourseService;
import com.amud.io.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bourses")
@CrossOrigin(origins = "http://localhost:4200")
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

    @PutMapping()
    ResponseEntity<ResponseVO<BourseDTO>> updateBourse(@RequestBody BourseDTO bourseDTO) {
        return this.bourseService.addBourse(bourseDTO);
    }

    @DeleteMapping
    ResponseEntity<ResponseVO<Void>> deleteBourse(@RequestParam("bourseId") Long bourseId) {
        return this.bourseService.deleteBourse(bourseId);
    }

    @GetMapping()
    ResponseEntity<ResponseVO<BourseDTO>> findBourseById(@RequestParam("bourseId") Long bourseId) {
        return  this.bourseService.findBourseById(bourseId);
    }
}
