package org.aemudapi.member.controller;

import lombok.AllArgsConstructor;
import org.aemudapi.member.dtos.ContactInfoRequestDto;
import org.aemudapi.member.service.ContactInfoService;
import org.aemudapi.utils.ResponseVO;
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
