package org.aemudapi.user.dtos;

public record ResetPasswordRequest(
        String password,
        String confirmPassword,
        String username,
        String token
) {}
