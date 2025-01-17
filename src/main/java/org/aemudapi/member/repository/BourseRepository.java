package org.aemudapi.member.repository;

import org.aemudapi.member.entity.Bourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BourseRepository extends JpaRepository<Bourse, String> {
}
