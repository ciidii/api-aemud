package com.amud.io.aemudapi.repositories;

import com.amud.io.aemudapi.entities.Bourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BourseRepository extends JpaRepository<Bourse, Long> {
}
