package com.amud.io.aemudapi.repositories;

import com.amud.io.aemudapi.entities.MonthContribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthContributionRepository extends JpaRepository<MonthContribution, Long> {
}
