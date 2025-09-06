package org.aemudapi.contribution.repository;

import org.aemudapi.contribution.entity.Contribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Month;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContributionRepository extends JpaRepository<Contribution, String> {

    @Query("""
            select count(c) from Contribution c
            """)
    Integer countContributions();

    @Query("""
            select count(c) from Contribution c where c.session.id = :sessionId
            """)
    Integer countContributionBySessionId(String sessionId);

    @Query("""
            select count(c) from Contribution c where c.month = :monthId
            """)
    int countContributionPeerMonth(String monthId);

    @Query("""
            select c from Contribution c where c.month = :monthId and c.session.id = :sessionId
            """)
    List<Contribution> findContributionByMonth(String monthId, String sessionId);

    @Query("""
            select c from Contribution c where c.member.id = :memberId and c.session.id = :sessionId
            """)
    List<Contribution> findMemberContributionsBySessionId(String memberId, String sessionId);

    @Query("""
        select count(c) > 0 from Contribution c 
        where c.member.id = :memberId 
          and c.session.id = :sessionId 
          and c.month = :month
       """)
    boolean existsByMemberAndSessionAndMonth(String memberId, String sessionId, YearMonth month);


//    @Query("""
//            select sum(c.amount) from Contribution c where c.session.id = :sessionId
//            """)
//    Double sumContributionsBySessionId(String sessionId);

//    @Query("""
//            select sum(c.amount) from Contribution c where c.session.id = :sessionId and c.month.id = :monthId
//            """)
//    Double sumContributionsByMonth(String sessionId, String monthId);

//    @Query("""
//            select c from Contribution c where c.session.id = :sessionId and c.month.id = :monthId and c.member.contactInfo.numberPhone=:memberId
//            """)
//    List<Contribution> findMonthMemberByPhoneNumberContribution(@Param("sessionId") String sessionId, @Param("monthId") String monthId, @Param("memberId") String memberId);
}
