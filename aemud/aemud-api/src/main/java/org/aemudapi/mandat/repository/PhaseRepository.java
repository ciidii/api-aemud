package org.aemudapi.mandat.repository;

import org.aemudapi.mandat.entity.Phase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PhaseRepository extends JpaRepository<Phase, String> {
}
