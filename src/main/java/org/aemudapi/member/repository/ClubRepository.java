package org.aemudapi.member.repository;

import org.aemudapi.member.entity.Club;
import org.springframework.data.repository.CrudRepository;

public interface ClubRepository extends CrudRepository<Club, Long> {
}