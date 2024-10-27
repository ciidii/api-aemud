package com.amud.io.aemudapi.controllers;

import com.amud.io.aemudapi.dto.CommissionDto;
import com.amud.io.aemudapi.services.CommissionService;
import com.amud.io.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("commissions")
@CrossOrigin(origins = "http://localhost:4200")
public class CommissionController {
    private final CommissionService commissionService;

    public CommissionController(CommissionService commissionService) {
        this.commissionService = commissionService;
    }

    @PostMapping()
    public ResponseEntity<ResponseVO<CommissionDto>> addCommission(@RequestBody CommissionDto commission){
        return this.commissionService.addCommission(commission);
    }

    @GetMapping("all")
    public ResponseEntity<ResponseVO<List<CommissionDto>>> getAllCommissions(){
        return this.commissionService.getAllCommission();
    }
}