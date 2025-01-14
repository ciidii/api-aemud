package org.aemudapi.member.repository;

import org.aemudapi.member.entity.PersonToCall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonToCallRepository extends JpaRepository<PersonToCall, Long> {
}