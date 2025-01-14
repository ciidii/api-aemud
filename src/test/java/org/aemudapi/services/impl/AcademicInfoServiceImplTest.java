package org.aemudapi.services.impl;

import org.aemudapi.member.dtos.AcademicInfoRequestDTO;
import org.aemudapi.member.entity.MemberAndYearKey;
import org.aemudapi.member.entity.MembershipInfo;
import org.aemudapi.member.entity.PersonalInfo;
import org.aemudapi.member.entity.YearOfSession;
import org.aemudapi.member.entity.*;
import org.aemudapi.member.mapper.AcademicInfoMapper;
import org.aemudapi.member.repository.AcademicInfoRepository;
import org.aemudapi.member.repository.YearOfSessionRepository;
import org.aemudapi.member.service.AcademicInfoService;
import org.aemudapi.member.service.impl.AcademicInfoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
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
    private YearOfSessionRepository yearOfSessionRepository;
    AcademicInfo academicInfo;
    YearOfSession yearOfSession = new YearOfSession();
    Member member = new Member();
    MembershipInfo membershipInfo = new MembershipInfo();
    MemberAndYearKey memberAndYearKey = new MemberAndYearKey();


    @BeforeEach
    void setUp() throws Exception {
        this.underTest = new AcademicInfoServiceImpl(academicInfoMapper, academicInfoRepository, yearOfSessionRepository);

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


        yearOfSession.setCurrentYear(true);
        yearOfSession.setYear_(2000);
        yearOfSession.setIdYear(1L);
        membershipInfo.setYearOfMembership(yearOfSession);

        member.setMembershipInfo(membershipInfo);
        memberAndYearKey.setYearOfRegistration(2020L);
    }

    @Test
    void createAcademicInfo() {
        AcademicInfoRequestDTO academicInfoRequestDTO = new AcademicInfoRequestDTO();
        academicInfo = new AcademicInfo(memberAndYearKey, member, yearOfSession, "Ucad", "fst", "MI", "Section", "L1");
        academicInfoRequestDTO = academicInfoMapper.toDto(academicInfo);
        //when
        this.underTest.createAcademicInfo(academicInfoRequestDTO);
        //then
        ArgumentCaptor<AcademicInfoRequestDTO> captor = ArgumentCaptor.forClass(AcademicInfoRequestDTO.class);
        verify(academicInfoRepository).save(academicInfo);

        AcademicInfoRequestDTO captorValue = captor.getValue();
        assertThat(captorValue).isEqualTo(academicInfoRequestDTO);
    }

    @Test
    void getCurrentSessionMemberAcademicInfo() {
    }
}