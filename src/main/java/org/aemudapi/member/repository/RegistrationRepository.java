package org.aemudapi.member.repository;

import org.aemudapi.member.entity.Member;
import org.aemudapi.member.entity.Registration;
import org.aemudapi.member.entity.RegistrationStatus;
import org.aemudapi.member.entity.TypeInscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RegistrationRepository extends JpaRepository<Registration, String> {

    @Query("""
            SELECT count(*) FROM Registration r where r.session.id =:session
            """)
    int getRegistrationCountBySession(@Param("session") String session);

    @Query("""
            SELECT count(*) FROM Registration r where r.session.id =:session and r.statusPayment=:statusPayment
            """)
    int getPayedOrNoPayedSessionCountPeerSession(@Param("session") String session, @Param("statusPayment") Boolean statusPayment);

    @Query("""
            SELECT r.member FROM Registration r where r.session.id =:session
            """)
    List<Member> getMembersBySession(@Param("session") String session);

    @Query("""
            SELECT r.member FROM Registration r where r.session.id =:session and r.statusPayment=:statusPayment
            """)
    List<Member> getPayedOrNoPayedMembersPeerSession(@Param("session") String session, @Param("statusPayment") Boolean statusPayment);

    @Query("""
            SELECT r.member FROM Registration r where r.session.id =:session and r.registrationStatus =:registrationStatus
            """)
    List<Member> getMembersRegistrationsStatusForSessions(@Param("session") String session, @Param("registrationStatus") RegistrationStatus registrationStatus);

    @Query("""
            select r.member from Registration r where r.session.id =:session and r.member.id =:memberId
            """)
    Optional<Member> findMemberRegisteredMemberForSession(@Param("session") String session, @Param("memberId") String memberId);


    @Query("""
            SELECT count(*) FROM Registration r where r.session.id =:session AND r.registrationType =:typeInscription
            """)
    int getNewOrRenewalAdherentForASession(@Param("session") String session,@Param("typeInscription") TypeInscription typeInscription);
}
