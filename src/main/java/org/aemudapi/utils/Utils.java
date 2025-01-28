package org.aemudapi.utils;

import org.aemudapi.member.dtos.FilterDTO;
import org.aemudapi.member.entity.Member;
import org.aemudapi.member.specifications.MemberSpecification;
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
            if (filterDTO.getClub() != null && !filterDTO.getClub().isEmpty()) {
                memberSpecification = memberSpecification.and(MemberSpecification.hasClub(filterDTO.getClub()));
            }

            if (filterDTO.getCommission() != null && !filterDTO.getCommission().isEmpty()) {
                memberSpecification = memberSpecification.and(MemberSpecification.hasCommission(filterDTO.getCommission()));
            }
            if (filterDTO.getBourse() != null && !filterDTO.getBourse().isEmpty()) {
                memberSpecification = memberSpecification.and(MemberSpecification.hasBourse(filterDTO.getBourse()));
            }
            if (filterDTO.getRegistrationStatus() != null) {
                memberSpecification = memberSpecification.and(MemberSpecification.registrationStatus(filterDTO.getRegistrationStatus()));
            }
            if (filterDTO.getStatusPayment() != null) {
                if (filterDTO.getStatusPayment().equalsIgnoreCase("oui")) {
                    memberSpecification = memberSpecification.and(MemberSpecification.havePayed(true));

                } else if (filterDTO.getStatusPayment().equalsIgnoreCase("non")) {
                    memberSpecification = memberSpecification.and(MemberSpecification.havePayed(false));
                }
            }
        }
        return memberSpecification;
    }
}
