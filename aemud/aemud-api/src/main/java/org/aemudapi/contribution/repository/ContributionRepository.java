package org.aemudapi.contribution.repository;

import org.aemudapi.contribution.entity.Contribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.List;

@Repository
public interface ContributionRepository extends JpaRepository<Contribution, String> {

    @Query("""
            select count(c) from Contribution c
            """)
    Integer countContributions();

    @Query("""
            select count(c) from Contribution c where c.phase.id = :mandatId
            """)
    Integer countContributionByMandatId(String mandatId);

    @Query("""
            select count(c) from Contribution c where c.month = :monthId
            """)
    int countContributionPeerMonth(String monthId);

    @Query("""
            select c from Contribution c where c.month = :monthId and c.phase.id = :mandatId
            """)
    List<Contribution> findContributionByMonth(String monthId, String mandatId);

    @Query("""
            select c from Contribution c where c.member.id = :memberId and c.phase.id = :mandatId
            """)
    List<Contribution> findMemberContributionsByMandatId(String memberId, String mandatId);

    @Query("""
             select count(c) > 0 from Contribution c
             where c.member.id = :memberId
               and c.phase.id = :phaseId
               and c.month = :month
            """)
    boolean existsByMemberAndPhaseAndMonth(String memberId, String phaseId, YearMonth month);

    @Query("""
            select c from Contribution c where c.member.id = :memberId and c.phase.id = :phaseId""")
    List<Contribution> findMemberContributionsCalendarByMemberIdAndMandatId(String memberId, String phaseId);

}
