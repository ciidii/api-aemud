package org.aemudapi.repositories;

import org.aemudapi.member.entity.Session;
import org.aemudapi.member.repository.YearOfSessionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class YearOfSessionServiceRepositoryTest {

    @Autowired
    private YearOfSessionRepository yearOfSessionRepository;

    @Test
    void updateCurrentYear() {
        //given
        Session session = new Session(1L, 2020, true);
        yearOfSessionRepository.save(session);
        //when
        this.yearOfSessionRepository.updateCurrentYear();
        Session year = this.yearOfSessionRepository.findById(1L).orElseThrow();
        //then
        assertThat(year.isCurrentYear()).isTrue();
    }

    @Test
    void checkIfTheCurrentYearExists() {
        //given
        Session session = new Session(1L, 2020, true);
        this.yearOfSessionRepository.save(session);
        //when
        //SessionService year = this.yearOfSessionRepository.findCurrentSession();
        //then
       // assertThat(year.isCurrentYear()).isTrue();
    }
}