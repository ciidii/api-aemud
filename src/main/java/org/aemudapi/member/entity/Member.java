    package org.aemudapi.member.entity;

    import jakarta.persistence.*;
    import lombok.Data;

    @Entity
    @Table(name = "member")
    @Data
    public class Member {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Embedded
        private PersonalInfo personalInfo;
        @Embedded
        private MembershipInfo membershipInfo;

        public Member() {
            //constructor without args
        }
    }
