package org.aemudapi.user.dtos;

public record ChangePasswordRequestDTO(String password,String confirmPassword,
                                       String username) {
}
