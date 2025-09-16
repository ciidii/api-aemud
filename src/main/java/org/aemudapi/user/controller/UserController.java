package org.aemudapi.user.controller;

import lombok.AllArgsConstructor;
import org.aemudapi.user.dtos.*;
import org.aemudapi.user.services.UserService;
import org.aemudapi.utils.ResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ResponseVO<Void>> createUser(@RequestBody UserRequestDto userRequestDto) {
        return this.userService.createUser(userRequestDto);
    }

    @GetMapping("user-by-username")
    public ResponseEntity<ResponseVO<UserResponseDto>> getUserByUsername(@RequestParam("username") String username) {
        return this.userService.getUserByUsername(username);
    }

    @PostMapping("change-password-first-connection")
    public ResponseEntity<ResponseVO<Void>> changePasswordFirstConnection(@RequestBody ChangePasswordRequestDTO changePasswordRequestDTO) {
        return this.userService.changePasswordFirstConnection(changePasswordRequestDTO);
    }

    @PostMapping("forgotten-password-email")
    ResponseEntity<ResponseVO<Boolean>> checkIfEmailValid(@RequestBody EmailDTO email) {
        return this.userService.checkIfEmailValid(email.email());
    }

    @PostMapping("check-valide-token")
    Boolean checkTokenValid(@RequestBody TokeEmailDTO tokeEmailDTO) {
        return this.userService.checkTokenValid(tokeEmailDTO.email(), tokeEmailDTO.token());
    }

    @PostMapping("change-password-forgotten")
    public ResponseEntity<ResponseVO<Void>> changeChangeWordPForgotten(@RequestBody ChangeForgottenPassword changeForgottenPassword) {
        return this.userService.changeChangeWordPForgotten(changeForgottenPassword);
    }
}