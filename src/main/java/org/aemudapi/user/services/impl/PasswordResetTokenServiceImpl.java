package org.aemudapi.user.services.impl;

import lombok.AllArgsConstructor;
import org.aemudapi.user.entity.PasswordResetToken;
import org.aemudapi.user.repository.PasswordResetTokenRepository;
import org.aemudapi.user.services.PasswordResetTokenService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {
    private PasswordResetTokenRepository passwordResetTokenRepository;
    @Override
    public PasswordResetToken createPasswordResetToken(PasswordResetToken passwordResetToken) {
        this.passwordResetTokenRepository.save(passwordResetToken);
        return null;
    }
}
