package org.aemudapi.member.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommissionDto {
    private List<String> members;
    private String id;
    private String name;
}
