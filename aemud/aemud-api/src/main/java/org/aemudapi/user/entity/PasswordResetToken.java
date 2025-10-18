package org.aemudapi.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aemudapi.member.entity.BaseEntity;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PasswordResetToken extends BaseEntity {
    
    private String token;

    @Column(nullable = false)
    private String email;

    private LocalDateTime expiryDate;

    private boolean used = false;

}
