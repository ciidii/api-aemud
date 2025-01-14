package org.aemudapi.member.dtos;

import lombok.Data;

@Data
public class YearOfSessionResponse {
    private Long id;
    private int year_;
    private boolean currentYear;
}
