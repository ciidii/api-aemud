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

    ResponseEntity<ResponseVO<Integer>> getRegistrationCountBySession(String session);

    ResponseEntity<ResponseVO<Integer>> getPayedOrNoPayedSessionCountPeerSession(String sessionID, Boolean statusPayment);

    ResponseEntity<ResponseVO<List<MemberDataResponseDTO>>> getMemberBySession(String sessionId);

    ResponseEntity<ResponseVO<List<MemberDataResponseDTO>>> getPayedOrNoPayedMembersPeerSession(String session, Boolean statusPayment);

    ResponseEntity<ResponseVO<List<MemberDataResponseDTO>>> getMembersRegistrationsStatusForSessions(String session, RegistrationStatus registrationStatus);

    ResponseEntity<ResponseVO<Integer>> getNewOrRenewalAdherentForASession(String session, TypeInscription typeInscription);

    ResponseEntity<ResponseVO<List<MemberDataResponseDTO>>> getMemberByYearOfRegistration(String session);
}
