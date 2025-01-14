package org.aemudapi.notification.repository;

import org.aemudapi.contribution.entity.MonthContribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthContributionRepository extends JpaRepository<MonthContribution, Long> {
}
