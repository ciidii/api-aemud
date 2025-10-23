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
            SELECT count(*) FROM Registration r where r.mandat.id =:mandatId
            """)
    int getRegistrationCountByMandat(@Param("mandatId") String mandatId);

    @Query("""
            SELECT count(*) FROM Registration r where r.mandat.id =:mandatId and r.statusPayment=:statusPayment
            """)
    int getPayedOrNoPayedSessionCountPeerMandat(@Param("mandatId") String mandatId, @Param("statusPayment") Boolean statusPayment);

    @Query("""
            SELECT r.member FROM Registration r where r.mandat.id =:mandatId
            """)
    List<Member> getMembersByMandat(@Param("mandatId") String mandatId);

    @Query("""
            SELECT r.member FROM Registration r where r.mandat.id =:mandatId and r.statusPayment=:statusPayment
            """)
    List<Member> getPayedOrNoPayedMembersPeerMandat(@Param("mandatId") String mandatId, @Param("statusPayment") Boolean statusPayment);

    @Query("""
            SELECT r.member FROM Registration r where r.mandat.id =:mandatId and r.registrationStatus =:registrationStatus
            """)
    List<Member> getMembersRegistrationsStatusForMandats(@Param("mandatId") String mandatId, @Param("registrationStatus") RegistrationStatus registrationStatus);

    @Query("""
            select r.member from Registration r where r.mandat.id =:mandatID and r.member.id =:memberId
            """)
    Optional<Member> findMemberRegisteredMemberForMandat(@Param("mandatID") String mandatID, @Param("memberId") String memberId);


    @Query("""
            SELECT count(*) FROM Registration r where r.mandat.id =:mandatId AND r.registrationType =:typeInscription
            """)
    int getNewOrRenewalAdherentForAMandat(@Param("mandatId") String mandatId,@Param("typeInscription") TypeInscription typeInscription);
}
