package com.amud.io.aemudapi.repositories;

import com.amud.io.aemudapi.entities.YearOfSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface YearOfSessionRepository extends JpaRepository<YearOfSession, Long> {
    @Modifying
    @Transactional
    @Query("update YearOfSession y set y.currentYear = false where y.currentYear = true")
    void updateCurrentYear();

    @Query("select y from YearOfSession y where y.currentYear=true")
    Optional<YearOfSession> findCurrentSession();
}
