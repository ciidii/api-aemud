package org.aemudapi.repositories;

import org.aemudapi.member.entity.YearOfSession;
import org.aemudapi.member.repository.YearOfSessionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class YearOfSessionRepositoryTest {

    @Autowired
    private YearOfSessionRepository yearOfSessionRepository;

    @Test
    void updateCurrentYear() {
        //given
        YearOfSession yearOfSession = new YearOfSession(1L, 2020, true);
        yearOfSessionRepository.save(yearOfSession);
        //when
        this.yearOfSessionRepository.updateCurrentYear();
        YearOfSession year = this.yearOfSessionRepository.findById(1L).orElseThrow();
        //then
        assertThat(year.isCurrentYear()).isTrue();
    }

    @Test
    void checkIfTheCurrentYearExists() {
        //given
        YearOfSession yearOfSession = new YearOfSession(1L, 2020, true);
        this.yearOfSessionRepository.save(yearOfSession);
        //when
        //YearOfSession year = this.yearOfSessionRepository.findCurrentSession();
        //then
       // assertThat(year.isCurrentYear()).isTrue();
    }
}