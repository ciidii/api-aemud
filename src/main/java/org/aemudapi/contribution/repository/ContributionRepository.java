package org.aemudapi.contribution.repository;

import org.aemudapi.contribution.entity.Contribution;
import org.aemudapi.contribution.entity.ContributionKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContributionRepository extends JpaRepository<Contribution, ContributionKey> {
}
