package com.amud.io.aemudapi.controllers;


import com.amud.io.aemudapi.dto.MemberDataRequestDTO;
import com.amud.io.aemudapi.services.MainFormService;
import com.amud.io.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "main-form")
public class MainFormController {
    private final MainFormService mainFormService;

    public MainFormController(MainFormService mainFormService) {
        this.mainFormService = mainFormService;
    }

    @PostMapping("add")
    public ResponseEntity<ResponseVO<Void>> register(@RequestBody MemberDataRequestDTO memberDataRequestDTO) {
        return this.mainFormService.registerMember(memberDataRequestDTO);
    }
}
