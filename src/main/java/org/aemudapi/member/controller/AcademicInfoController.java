package org.aemudapi.member.controller;

import lombok.AllArgsConstructor;
import org.aemudapi.member.dtos.AcademicInfoRequestDTO;
import org.aemudapi.member.service.AcademicInfoService;
import org.aemudapi.utils.ResponseVO;
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
    public ResponseEntity<ResponseVO<Void>> addAcademicInfo(@RequestBody AcademicInfoRequestDTO infoRequestDTO) {
        return this.academicInfoService.createAcademicInfo(infoRequestDTO);
    }
}
