package com.amud.io.aemudapi.controllers;

import com.amud.io.aemudapi.dto.ContactInfoRequestDto;
import com.amud.io.aemudapi.services.ContactInfoService;
import com.amud.io.aemudapi.utils.ResponseVO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("contact-infos")
@AllArgsConstructor
public class ContactInfoController {
    private final ContactInfoService contactInfoService;

    @PostMapping
    public ResponseEntity<ResponseVO<Void>> createContactInfos(@RequestBody ContactInfoRequestDto contactInfoRequestDto) {
        return this.contactInfoService.createContactInfo(contactInfoRequestDto);
    }
}
