package com.amud.io.aemudapi.utils;

import com.amud.io.aemudapi.Specifications.MemberSpecification;
import com.amud.io.aemudapi.dto.FilterDTO;
import com.amud.io.aemudapi.entities.Member;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class Utils {
    public static Specification<Member> makeFilterCriteriaSpec(String criteria, String value, FilterDTO filterDTO) {
        Specification<Member> memberSpecification = Specification.where(null);
        if (criteria != null && value != null) {
            if (Objects.equals(criteria, "firstname")) {
                memberSpecification = memberSpecification.and(MemberSpecification.hasLastname(value));
            } else if (criteria.equals("name")) {
                memberSpecification = memberSpecification.and(MemberSpecification.hasName(value));
            }
        }

        if (filterDTO != null) {
            if (filterDTO.getClub() != null) {
                memberSpecification = memberSpecification.and(MemberSpecification.hasClub(filterDTO.getClub()));
            }
            if (filterDTO.getYear() != null) {
                memberSpecification = memberSpecification.and(MemberSpecification.hasYearOfRegistration(filterDTO.getYear()));
            }
            if (filterDTO.getCommission() != null) {
                memberSpecification = memberSpecification.and(MemberSpecification.hasCommission(filterDTO.getCommission()));
            }
        }
        return memberSpecification;
    }
}
