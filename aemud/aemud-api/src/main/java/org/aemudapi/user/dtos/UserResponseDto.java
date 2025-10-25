package org.aemudapi.user.dtos;

import lombok.Getter;
import lombok.Setter;
import org.aemudapi.user.entity.Role;

import java.util.List;

@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String username;
    private boolean locked;
    private boolean forcePasswordChange;
    private String memberId;
    private List<Role> roles;
}