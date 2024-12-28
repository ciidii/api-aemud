package com.amud.io.aemudapi.controllers;

import com.amud.io.aemudapi.dto.ContactInfoRequestDto;
import com.amud.io.aemudapi.services.ContactInfoService;
import com.amud.io.aemudapi.utils.ResponseVO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("contact-infos")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ContactInfoController {
    private final ContactInfoService contactInfoService;

    @PostMapping
    public ResponseEntity<ResponseVO<Void>> createContactInfos(@RequestBody ContactInfoRequestDto contactInfoRequestDto) {
        return this.contactInfoService.createContactInfo(contactInfoRequestDto);
    }
}
