package com.amud.io.aemudapi.repositories;

import com.amud.io.aemudapi.entities.MemberHasBourse;
import com.amud.io.aemudapi.entities.MemberHasBourseKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberHasBourseRepository extends JpaRepository<MemberHasBourse, MemberHasBourseKey> {
}
