package org.aemudapi.services.impl;

import org.aemudapi.club.entity.Club;
import org.aemudapi.commission.entity.Commission;
import org.aemudapi.member.entity.MembershipInfo;
import org.aemudapi.member.entity.PersonalInfo;
import org.aemudapi.member.entity.Session;
import org.aemudapi.member.entity.*;
import org.aemudapi.member.mapper.AcademicInfoMapper;
import org.aemudapi.member.repository.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AcademicInfoServiceTest {
    @Mock
    private AcademicInfoRepository repository;
    private AcademicInfoService underTest;
    @Mock
    private AcademicInfoMapper academicInfoMapper;
    @Mock
    private AcademicInfoRepository academicInfoRepository;
    @Mock
    private SessionRepository sessionRepository;
    AcademicInfo academicInfo;
    Session session = new Session();
    Member member = new Member();
    MembershipInfo membershipInfo = new MembershipInfo();
    Member_Session_PK memberSessionPK = new Member_Session_PK();


    @BeforeEach
    void setUp() throws Exception {
        this.underTest = new AcademicInfoServiceImpl(academicInfoMapper, academicInfoRepository, sessionRepository);

        member.setId(1L);
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setName("John Doe");
        personalInfo.setBirthday(LocalDate.of(2000, 10, 11));
        personalInfo.setFirstname("John");
        personalInfo.setMaritalStatus("SINGLE");

        member.setPersonalInfo(personalInfo);


        membershipInfo.setAemudCourses("arabes");

        Club club = new Club();
        club.setId(1L);
        club.setName("Infomatique");

        membershipInfo.setClubs(List.of(club));

        Bourse bourse = new Bourse();
        bourse.setIdBourse(1L);
        bourse.setLebelle("Bourse entier");
        bourse.setMontant(2000.0);

        membershipInfo.setBourse(bourse);
        membershipInfo.setPay(false);
        membershipInfo.setBacSeries("S2");

        Commission commission = new Commission();
        commission.setId(1L);
        commission.setName("Commission CTIC");

        membershipInfo.setCommission(commission);

        membershipInfo.setLegacyInstitution("LBKG");
        membershipInfo.setOtherCourses("arabes");


        session.setCurrentYear(true);
        session.setYear_(2000);
        session.setIdYear(1L);
        membershipInfo.setYearOfMembership(session);

        member.setMembershipInfo(membershipInfo);
        memberSessionPK.setYearOfRegistration(2020L);
    }

    @Test
    void createAcademicInfo() {
    }

    @Test
    void getCurrentSessionMemberAcademicInfo() {
    }
}