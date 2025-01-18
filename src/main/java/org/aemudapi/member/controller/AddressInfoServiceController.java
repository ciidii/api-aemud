package org.aemudapi.member.controller;

import lombok.AllArgsConstructor;
import org.aemudapi.member.dtos.AddressInfoRequestDto;
import org.aemudapi.member.service.AddressInfoService;
import org.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("address-infos")
@AllArgsConstructor
public class AddressInfoServiceController {
    private final AddressInfoService addressInfoService;

    @PostMapping
    public ResponseEntity<ResponseVO<Void>> createAddressInfos(@RequestBody AddressInfoRequestDto requestDto) {
        return this.addressInfoService.createAddressInfo(requestDto);
    }

    @GetMapping
    public ResponseEntity<ResponseVO<AddressInfoRequestDto>> getCurrentSessionMemberAddress(@RequestParam String memberID) {
        return this.addressInfoService.getCurrentSessionMemberAddress(memberID);
    }
}
