package com.amud.io.aemudapi.repositories;

import com.amud.io.aemudapi.entities.PersonToCall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonToCallRepository extends JpaRepository<PersonToCall, Long> {
}
