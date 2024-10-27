package com.amud.io.aemudapi.repositories;

import com.amud.io.aemudapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
