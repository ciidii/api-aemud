package org.aemudapi.member.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FilterDTO {
  private Long  club;
  private Long  year;
  private Long  commission;

}
