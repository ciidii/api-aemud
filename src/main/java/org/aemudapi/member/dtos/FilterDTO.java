package org.aemudapi.member.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FilterDTO {
    private String club;
    private String year;
    private String commission;
    private String bourse;

}
