package org.aemudapi.user.services;

import org.aemudapi.user.dtos.ChangeForgottenPassword;
import org.aemudapi.user.dtos.ChangePasswordRequestDTO;
import org.aemudapi.user.dtos.UserRequestDto;
import org.aemudapi.user.dtos.UserResponseDto;
import org.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<ResponseVO<Void>> createUser(UserRequestDto userRequestDto);

    ResponseEntity<ResponseVO<UserResponseDto>> getUserByUsername(String username);

    ResponseEntity<ResponseVO<Void>> changePasswordFirstConnection(ChangePasswordRequestDTO changePasswordRequestDTO);

    ResponseEntity<ResponseVO<Boolean>> checkIfEmailValid(String email);

    Boolean checkTokenValid(String email,String token);

    ResponseEntity<ResponseVO<Void>> changeChangeWordPForgotten(ChangeForgottenPassword changePasswordRequestDTO);
}