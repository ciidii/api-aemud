package com.amud.io.aemudapi.repositories;

import com.amud.io.aemudapi.entities.Club;
import com.amud.io.aemudapi.entities.Commission;
import com.amud.io.aemudapi.entities.LogMemberBackup;
import com.amud.io.aemudapi.entities.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class LogMemberBackupRepositoryTest {

    @Autowired
    private LogMemberBackupRepository logMemberBackupRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CommissionRepository commissionRepository;

    @Autowired
    private ClubRepository clubRepository;

    private Commission commission;
    private List<Club> clubs;
    private List<Member> members;

    private static final String MEMBER_NAME = "Diallo";
    private static final String MEMBER_EMAIL = "boubacar@gmail.com";
    private static final String MEMBER_FIRSTNAME = "Boubacar";
    private static final String MEMBER_NATIONALITY = "SN";
    private static final String MEMBER_BIRTHDAY = "10/03/2000";
    private static final String MEMBER_MARITAL_STATUS = "Single";
    private static final String MEMBER_ADDRESS_IN_DAKAR = "Malika";
    private static final String MEMBER_HOLIDAY_ADDRESS = "Dakar";
    private static final String MEMBER_PHONE = "7777777777";
    private static final String MEMBER_PERSON_TO_CALL = "Tanou";
    private static final String MEMBER_FACULTY = "FSR";
    private static final String MEMBER_DEPARTMENT = "Math-Info";
    private static final String MEMBER_BOURSE = "bourse_entier";
    private static final String MEMBER_PARTICIPATED_ACTIVITY = "Set Setal";
    private static final String MEMBER_ADDRESS_TO_CAMPUS = "Pavillons H";
    private static final String MEMBER_AEMUD_COURSES = "Arabe";
    private static final String MEMBER_OTHER_COURSES = "No";
    private static final String MEMBER_YEAR_OF_MEMBERSHIP = "2019";
    private static final String MEMBER_TWINS_NAME = "Loum";
    private static final boolean MEMBER_PAY = false;
    private static final String MEMBER_POLITIC_ORGANISATION = "PASTEF";

    @BeforeEach
    void setUp() {
        commission = new Commission(null, "Commission");
        commission = commissionRepository.save(commission);

        clubs = createAndSaveClubs();
        members = createAndSaveMembers();
    }

    @AfterEach
    void tearDown() {
        logMemberBackupRepository.deleteAll();
        memberRepository.deleteAll();
        clubRepository.deleteAll();
        commissionRepository.deleteAll();
    }

    @Test
    void shouldNotFindMembersWhenBackupIsTrue() {
        saveLogMemberBackups(true, true);

        List<LogMemberBackup> logMemberBackups = logMemberBackupRepository.findAllWhereBackupIsFalse().orElseThrow();
        assertThat(logMemberBackups).isEmpty();
    }

    @Test
    void shouldFindAllMembersWhenBackupIsFalse() {
        saveLogMemberBackups(false, false);

        List<LogMemberBackup> logMemberBackups = logMemberBackupRepository.findAllWhereBackupIsFalse().orElseThrow();
        assertThat(logMemberBackups).hasSize(2);
    }

    private List<Club> createAndSaveClubs() {
        List<Club> clubs = new ArrayList<>();
        clubs.add(new Club(null, "Club Informatique"));
        clubs.add(new Club(null, "Club Maths"));
        return (List<Club>) clubRepository.saveAll(clubs);
    }

    private List<Member> createAndSaveMembers() {
        List<Member> members = new ArrayList<>();
        members.add(createMember());
        members.add(createMember());
        return (List<Member>) memberRepository.saveAll(members);
    }

    private Member createMember() {
        return Member.builder()
                .setName(MEMBER_NAME)
                .setEmail(MEMBER_EMAIL)
                .setFirstname(MEMBER_FIRSTNAME)
                .setNationality(MEMBER_NATIONALITY)
                .setBirthday(MEMBER_BIRTHDAY)
                .setMaritalStatus(MEMBER_MARITAL_STATUS)
                .setAddressInDakar(MEMBER_ADDRESS_IN_DAKAR)
                .setHolidayAddress(MEMBER_HOLIDAY_ADDRESS)
                .setNumberPhone(MEMBER_PHONE)
                .setPersonToCall(MEMBER_PERSON_TO_CALL)
                .setFaculty(MEMBER_FACULTY)
                .setDepartment(MEMBER_DEPARTMENT)
                .setClubs(clubs)
                .setCommission(commission)
                .setParticipatedActivity(MEMBER_PARTICIPATED_ACTIVITY)
                .setAddressToCampus(MEMBER_ADDRESS_TO_CAMPUS)
                .setAemudCourses(MEMBER_AEMUD_COURSES)
                .setOtherCourses(MEMBER_OTHER_COURSES)
                .setTwinsName(MEMBER_TWINS_NAME)
                .setPay(MEMBER_PAY)
                .setPoliticOrganisation(MEMBER_POLITIC_ORGANISATION)
                .build();
    }

    private void saveLogMemberBackups(boolean... backupStatuses) {
        for (int i = 0; i < backupStatuses.length; i++) {
            LogMemberBackup logMemberBackup = new LogMemberBackup();
            logMemberBackup.setBackup(backupStatuses[i]);
            logMemberBackup.setMember(members.get(i));
            logMemberBackupRepository.save(logMemberBackup);
        }
    }
}
