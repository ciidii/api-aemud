package org.aemudapi.member.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClubDto {
    private String id;
    private String name;
    private List<String> members;
}
