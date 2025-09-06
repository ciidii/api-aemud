package org.aemudapi.contribution.repository;

import org.aemudapi.contribution.entity.Payement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PayementRepository extends JpaRepository<Payement, UUID> {

}
