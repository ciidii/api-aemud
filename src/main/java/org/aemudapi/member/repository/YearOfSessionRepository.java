package org.aemudapi.member.repository;

import org.aemudapi.member.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface YearOfSessionRepository extends JpaRepository<Session, Long> {
    @Modifying
    @Transactional
    @Query("update Session y set y.currentYear = false where y.currentYear = true")
    void updateCurrentYear();

    @Query("select y from Session y where y.currentYear=true")
    Optional<Session> findCurrentSession();
}
