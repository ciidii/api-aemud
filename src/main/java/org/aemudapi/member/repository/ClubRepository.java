package org.aemudapi.member.repository;

import org.aemudapi.club.entity.Club;
import org.springframework.data.repository.CrudRepository;

public interface ClubRepository extends CrudRepository<Club, Long> {
}