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
            select count(c) from Contribution c where c.mandat.id = :mandatId
            """)
    Integer countContributionByMandatId(String mandatId);

    @Query("""
            select count(c) from Contribution c where c.month = :monthId
            """)
    int countContributionPeerMonth(String monthId);

    @Query("""
            select c from Contribution c where c.month = :monthId and c.mandat.id = :mandatId
            """)
    List<Contribution> findContributionByMonth(String monthId, String mandatId);

    @Query("""
            select c from Contribution c where c.member.id = :memberId and c.mandat.id = :mandatId
            """)
    List<Contribution> findMemberContributionsByMandatId(String memberId, String mandatId);

    @Query("""
             select count(c) > 0 from Contribution c 
             where c.member.id = :memberId 
               and c.mandat.id = :mandatId 
               and c.month = :month
            """)
    boolean existsByMemberAndMandatAndMonth(String memberId, String mandatId, YearMonth month);

    @Query("""
            select c from Contribution c where c.member.id = :memberId and c.mandat.id = :mandatId""")
    List<Contribution> findMemberContributionsCalendarByMemberIdAndMandatId(String memberId, String mandatId);

}
