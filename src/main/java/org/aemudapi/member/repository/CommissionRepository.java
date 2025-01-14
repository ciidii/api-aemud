package org.aemudapi.member.repository;

import org.aemudapi.member.entity.Commission;
import org.springframework.data.repository.CrudRepository;

public interface CommissionRepository extends CrudRepository<Commission, Long> {
}
