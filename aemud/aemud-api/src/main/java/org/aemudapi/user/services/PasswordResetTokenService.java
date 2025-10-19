package org.aemudapi.user.services;

import org.aemudapi.user.entity.PasswordResetToken;

public interface PasswordResetTokenService {
    public PasswordResetToken createPasswordResetToken(PasswordResetToken passwordResetToken);
}
