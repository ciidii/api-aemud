package org.aemudapi.member.repository;

import org.aemudapi.member.entity.Member;
import org.aemudapi.member.entity.Registration;
import org.aemudapi.member.entity.RegistrationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RegistrationRepository extends JpaRepository<Registration, String> {

    @Query("""
            SELECT count(*) FROM Registration r where r.session.id =:session
            """)
    int getRegistrationCountBySession(String session);

    @Query("""
            SELECT count(*) FROM Registration r where r.session.id =:session and r.statusPayment=:statusPayment
            """)
    int getPayedOrNoPayedSessionCountPeerSession(String session, Boolean statusPayment);

    @Query("""
            SELECT r.member FROM Registration r where r.session.id =:session
            """)
    List<Member> getMembersBySession(String session);

    @Query("""
            SELECT r.member FROM Registration r where r.session.id =:session and r.statusPayment=:statusPayment
            """)
    List<Member> getPayedOrNoPayedMembersPeerSession(String session, Boolean statusPayment);

    @Query("""
            SELECT r.member FROM Registration r where r.session.id =:session and r.registrationStatus =:registrationStatus
            """)
    List<Member> getMembersRegistrationsStatusForSessions(String session, RegistrationStatus registrationStatus);
}
