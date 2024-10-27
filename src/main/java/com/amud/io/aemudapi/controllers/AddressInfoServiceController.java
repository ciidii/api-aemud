package com.amud.io.aemudapi.controllers;

import com.amud.io.aemudapi.dto.AddressInfoRequestDto;
import com.amud.io.aemudapi.dto.ContactInfoRequestDto;
import com.amud.io.aemudapi.services.AddressInfoService;
import com.amud.io.aemudapi.utils.ResponseVO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("address-infos")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")
public class AddressInfoServiceController {
    private final AddressInfoService addressInfoService;

    @PostMapping
    public ResponseEntity<ResponseVO<Void>> createAddressInfos(@RequestBody AddressInfoRequestDto requestDto) {
        return this.addressInfoService.createAddressInfo(requestDto);
    }

    @GetMapping
    public ResponseEntity<ResponseVO<AddressInfoRequestDto>> getCurrentSessionMemberAddress(@RequestParam Long memberID) {
        return this.addressInfoService.getCurrentSessionMemberAddress(memberID);
    }
}
