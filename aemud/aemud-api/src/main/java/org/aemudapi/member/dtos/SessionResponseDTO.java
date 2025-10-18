package org.aemudapi.member.dtos;

import lombok.Data;

@Data
public class SessionResponseDTO {
    private String id;
    private int session;
    private boolean currentYear;
}
