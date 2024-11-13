package com.amud.io.aemudapi.repositories;

import com.amud.io.aemudapi.entities.Contribution;
import com.amud.io.aemudapi.entities.ContributionKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContributionRepository extends JpaRepository<Contribution, ContributionKey> {
}
