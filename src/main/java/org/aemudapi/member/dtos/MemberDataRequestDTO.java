package org.aemudapi.member.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDataRequestDTO {
    private MemberRequestDto member;
    private AcademicInfoRequestDTO academicInfo;
    private AddressInfoRequestDto addressInfo;
    private ContactInfoRequestDto contactInfo;
}
