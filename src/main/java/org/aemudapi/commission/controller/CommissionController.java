package org.aemudapi.commission.controller;

import org.aemudapi.member.dtos.CommissionDto;
import org.aemudapi.member.service.CommissionService;
import org.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("commissions")
public class CommissionController {
    private final CommissionService commissionService;

    public CommissionController(CommissionService commissionService) {
        this.commissionService = commissionService;
    }

    @PostMapping()
    public ResponseEntity<ResponseVO<CommissionDto>> addCommission(@RequestBody CommissionDto commission) {
        return this.commissionService.addCommission(commission);
    }

    @GetMapping("all")
    public ResponseEntity<ResponseVO<List<CommissionDto>>> getAllCommissions() {
        return this.commissionService.getAllCommission();
    }

    @DeleteMapping()
    public ResponseEntity<ResponseVO<Void>> deleteCommission(@RequestParam("commissionId") String commissionID) {
        return this.commissionService.deleteCommission(commissionID);
    }

    @GetMapping()
    public ResponseEntity<ResponseVO<CommissionDto>> getCommissionById(@RequestParam("commissionId") String commissionID) {
        return this.commissionService.getSingleCommission(commissionID);
    }

    @PutMapping()
    public ResponseEntity<ResponseVO<CommissionDto>> updateCommission(@RequestBody CommissionDto commission) {
        return this.commissionService.updateCommission(commission);
    }
}
