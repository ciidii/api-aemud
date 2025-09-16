package org.aemudapi.user.dtos;


public record ChangeForgottenPassword(String password, String confirmPassword,
                                      String username, String token) {
}
