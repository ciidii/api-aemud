package org.aemudapi.member.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionRequestDTO {
    private String id;
    private boolean currentYear;
    private int session;
}
