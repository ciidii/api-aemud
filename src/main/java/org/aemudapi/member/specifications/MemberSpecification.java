package org.aemudapi.member.specifications;

import org.aemudapi.member.entity.Member;
import org.springframework.data.jpa.domain.Specification;

public class MemberSpecification {
    public static Specification<Member> hasName(String name) {
        return (root, query, criteriaBuilder) -> {
            query.multiselect(root.get("personalInfo").get("name"));
            return criteriaBuilder.like(root.get("personalInfo").get("name"), "%" + name + "%");
        };
    }

    public static Specification<Member> hasLastname(String firstname) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("personalInfo").get("firstname"), "%" + firstname.toLowerCase() + "%");
    }

    public static Specification<Member> hasClub(String clubId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.    get("clubs").get("id"), clubId);
    }

    public static Specification<Member> hasYearOfRegistration(String yearOfRegistration) {
        return (root, query, criteriaBuilder) -> yearOfRegistration == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("member").get("yearOfMembership").get("id"), yearOfRegistration);
    }

    public static Specification<Member> hasCommission(String commissionId) {
        return (root, query, criteriaBuilder) -> commissionId == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("commissions").get("id"), commissionId);
    }

    public static Specification<Member> hasBourse(String bourseId) {
        return (root, query, criteriaBuilder) -> bourseId == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("bourse").get("id"), bourseId);
    }
}
