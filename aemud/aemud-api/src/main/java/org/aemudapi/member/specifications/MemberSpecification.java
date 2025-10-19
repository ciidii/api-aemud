package org.aemudapi.member.specifications;

import jakarta.persistence.criteria.Join;
import org.aemudapi.member.entity.Member;
import org.aemudapi.member.entity.Registration;
import org.aemudapi.member.entity.RegistrationStatus;
import org.aemudapi.member.entity.TypeInscription;
import org.springframework.data.jpa.domain.Specification;

public class MemberSpecification {
    public static Specification<Member> hasName(String name) {
        return (root, query, criteriaBuilder) -> {
            query.multiselect(root.get("personalInfo").get("name"));
            // Conversion en minuscules pour une comparaison insensible à la casse
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("personalInfo").get("name")), "%" + name.toLowerCase() + "%");
        };
    }

    public static Specification<Member> hasPhone(String name) {
        return (root, query, criteriaBuilder) -> {
            query.multiselect(root.get("contactInfo").get("numberPhone"));
            // Conversion en minuscules pour une comparaison insensible à la casse
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("contactInfo").get("numberPhone")), "%" + name.toLowerCase() + "%");
        };
    }

    public static Specification<Member> hasLastname(String firstname) {
        return (root, query, criteriaBuilder) -> {
            // Conversion en minuscules pour comparaison insensible à la casse
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("personalInfo").get("firstname")), "%" + firstname.toLowerCase() + "%");
        };
    }

    public static Specification<Member> hasClub(String clubId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("clubs").get("id"), clubId);
    }


    public static Specification<Member> hasCommission(String commissionId) {
        return (root, query, criteriaBuilder) -> commissionId == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("commissions").get("id"), commissionId);
    }

    public static Specification<Member> hasBourse(String bourseId) {
        return (root, query, criteriaBuilder) -> bourseId == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("bourse").get("id"), bourseId);
    }

    public static Specification<Member> havePayed(boolean paymentStatus) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("registration").get("statusPayment"), paymentStatus);
    }

    public static Specification<Member> registrationStatus(RegistrationStatus registrationStatus) {
        return (root, query, criteriaBuilder) -> {
            query.distinct(true);
            return criteriaBuilder.equal(root.get("registration").get("registrationStatus"), registrationStatus);
        };
    }

    public static Specification<Member> registeredMembersForYear(String sessionId) {
        return (root, query, criteriaBuilder) -> {
            if (Member.class.equals(query.getResultType())) {
                root.fetch("registration");
            }

            Join<Member, Registration> registrationJoin = root.join("registration");

            return criteriaBuilder.equal(registrationJoin.get("session").get("id"), sessionId);
        };
    }

    public static Specification<Member> registrationType(TypeInscription registrationType) {
        return (root, query, criteriaBuilder) -> {
            query.distinct(true);
            return criteriaBuilder.equal(root.get("registration").get("registrationType"), registrationType);
        };
    }
}
