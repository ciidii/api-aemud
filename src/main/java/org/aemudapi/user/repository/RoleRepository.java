package org.aemudapi.user.repository;

import org.aemudapi.user.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
