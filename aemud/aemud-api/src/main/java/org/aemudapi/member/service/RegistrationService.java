package org.aemudapi.member.service;

import org.aemudapi.member.dtos.MemberDataResponseDTO;
import org.aemudapi.member.dtos.RegistrationRequestDto;
import org.aemudapi.member.dtos.RegistrationRequestWithPhoneNumberDto;
import org.aemudapi.member.entity.RegistrationStatus;
import org.aemudapi.member.entity.TypeInscription;
import org.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RegistrationService {
    ResponseEntity<ResponseVO<Void>> registerMember(RegistrationRequestDto registrationRequestDto);

    ResponseEntity<ResponseVO<Void>> registerMemberWithNumberPhone(RegistrationRequestWithPhoneNumberDto registrationRequestDto);

    ResponseEntity<ResponseVO<Void>> deleteRegistration(String id);

    ResponseEntity<ResponseVO<Integer>> getRegistrationCountByMandat(String mandatId);

    ResponseEntity<ResponseVO<Integer>> getPayedOrNoPayedSessionCountPeerMandat(String mandatId, Boolean statusPayment);

    ResponseEntity<ResponseVO<List<MemberDataResponseDTO>>> getMemberByMandat(String mandatId);

    ResponseEntity<ResponseVO<List<MemberDataResponseDTO>>> getPayedOrNoPayedMembersPeerMandat(String mandatId, Boolean statusPayment);

    ResponseEntity<ResponseVO<List<MemberDataResponseDTO>>> getMembersRegistrationsStatusForMandats(String mandatId, RegistrationStatus registrationStatus);

    ResponseEntity<ResponseVO<Integer>> getNewOrRenewalAdherentForAMandat(String mandatId, TypeInscription typeInscription);
}
