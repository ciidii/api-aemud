package org.aemudapi.member.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EngagementsRequestDTO {
    private String bourse;
    private List<String> clubs;
    private List<String> commissions;

}
