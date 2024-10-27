package com.amud.io.aemudapi.repositories;

import com.amud.io.aemudapi.entities.Member;
import com.amud.io.aemudapi.entities.ReRegistration;
import com.amud.io.aemudapi.entities.MemberAndYearKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReRegistrationRepository extends JpaRepository<ReRegistration, MemberAndYearKey> {
    /*

    @Query("select rr from ReRegistration rr where rr.reRegistrationKey.member = :id ")
    List<ReRegistration> findAllByMember(@Param("id")Long id);
    @Override
    Optional<ReRegistration> findById(MemberAndYearKey memberAndYearKey);

    @Query("SELECT m FROM Member m WHERE m.yearOfMembership.idYear = :year OR m.id IN (SELECT r.reRegistrationKey.member FROM ReRegistration r WHERE r.reRegistrationKey.yearOfRegistration = :year)")
    Optional<List<Member>> findAllByYearOfReRegistration(@Param("year") Long year);


    @Query("SELECT m FROM Member m WHERE m.id NOT IN (SELECT r.reRegistrationKey.member FROM ReRegistration r WHERE r.reRegistrationKey.yearOfRegistration = :year) and m.yearOfMembership.idYear< :year")
    Optional<List<Member>> findMembersNotRegisteredByYear(@Param("year") Long year);

     */
}

