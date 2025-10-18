package org.aemudapi.user.dtos;

import lombok.Data;
import org.aemudapi.user.entity.ROLES;

@Data
public class UserRequestDto {
    private String memberId;
    private ROLES [] roles;
}