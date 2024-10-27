package com.amud.io.aemudapi.repositories;

import com.amud.io.aemudapi.entities.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("select m from Member m where m.membershipInfo.yearOfMembership.currentYear=true")
    Page<Member> findAll(Pageable pageable);
}
