package org.aemudapi.contribution.repository;

import org.aemudapi.contribution.entity.Month;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthRepository extends JpaRepository<Month, String> {
}
