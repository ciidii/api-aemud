package org.aemudapi.mandat.repository;

import org.aemudapi.mandat.entity.Mandat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MandatRepository extends JpaRepository<Mandat, String> {
}
