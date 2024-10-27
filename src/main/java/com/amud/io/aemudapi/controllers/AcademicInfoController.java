package com.amud.io.aemudapi.controllers;

import com.amud.io.aemudapi.dto.AcademicInfoRequestDTO;
import com.amud.io.aemudapi.services.AcademicInfoService;
import com.amud.io.aemudapi.utils.ResponseVO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("academic-infos")
@AllArgsConstructor
public class AcademicInfoController {
    private final AcademicInfoService academicInfoService;
    @PostMapping
    public ResponseEntity<ResponseVO<Void>> addAcademicInfo(@RequestBody AcademicInfoRequestDTO infoRequestDTO){
        return this.academicInfoService.createAcademicInfo(infoRequestDTO);
    }
}
