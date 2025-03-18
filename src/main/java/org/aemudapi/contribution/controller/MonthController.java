package org.aemudapi.contribution.controller;

import lombok.AllArgsConstructor;
import org.aemudapi.contribution.dto.MonthDTO;
import org.aemudapi.contribution.service.MonthService;
import org.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("month")
@AllArgsConstructor
public class MonthController {
    private final MonthService monthService;

    @PostMapping("month")
    public ResponseEntity<ResponseVO<Void>> addMonth(@RequestBody MonthDTO month) {
        return this.monthService.addMonth(month);
    }

    @PostMapping("months")
    public ResponseEntity<ResponseVO<Void>> addMonth(@RequestBody List<MonthDTO> month) {
        return this.monthService.addMonth(month);
    }

    @PutMapping
    public ResponseEntity<ResponseVO<Void>> updateMonth(MonthDTO month) {
        return this.monthService.updateMonth(month);
    }

    @DeleteMapping
    public ResponseEntity<ResponseVO<Void>> deleteMonth(MonthDTO month) {
        return this.monthService.deleteMonth(month);
    }

    @GetMapping
    public ResponseEntity<ResponseVO<List<MonthDTO>>> getMonths() {
        return this.monthService.getAllMonth();
    }
}
