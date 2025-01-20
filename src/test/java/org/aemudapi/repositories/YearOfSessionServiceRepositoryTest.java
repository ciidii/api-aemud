package org.aemudapi.repositories;

import org.aemudapi.member.entity.Session;
import org.aemudapi.member.repository.SessionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class YearOfSessionServiceRepositoryTest {

    @Autowired
    private SessionRepository sessionRepository;

    @Test
    void updateCurrentYear() {
        //given
        Session session = new Session(1L, 2020, true);
        sessionRepository.save(session);
        //when
        this.sessionRepository.updateCurrentYear();
        Session year = this.sessionRepository.findById(1L).orElseThrow();
        //then
        assertThat(year.isCurrentYear()).isTrue();
    }

    @Test
    void checkIfTheCurrentYearExists() {
        //given
        Session session = new Session(1L, 2020, true);
        this.sessionRepository.save(session);
        //when
        //SessionService year = this.yearOfSessionRepository.findCurrentSession();
        //then
       // assertThat(year.isCurrentYear()).isTrue();
    }
}