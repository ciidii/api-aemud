package org.aemudapi.contribution.repository;

import org.aemudapi.member.entity.Contribution;
import org.aemudapi.member.entity.ContributionKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContributionRepository extends JpaRepository<Contribution, ContributionKey> {
}
