package com.amud.io.aemudapi.repositories;

import com.amud.io.aemudapi.projection.MemberThatContributeDto;
import com.amud.io.aemudapi.entities.Contribution;
import com.amud.io.aemudapi.entities.ContributionKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContributionRepository extends JpaRepository<Contribution, ContributionKey> {
    /*
    @Query("select m.id as id,m.name as name,m.firstname as firstname,m.numberPhone as numberPhone,m.email as email,c.amount as amount " +
            "from Member m join Contribution c on m.id = c.contributionKey.memberId " +
            "where c.contributionKey.idMonth=:idMonth " +
            "and c.contributionKey.idYear= :idYear")
    public List<MemberThatContributeDto> findAllContributeForAMonthOfAYear(@Param("idMonth") Long idMonth, @Param("idYear") Long idYear);

    @Query("select m.id as id,m.name as name,m.firstname as firstname,m.numberPhone as numberPhone,m.email as email, mhb.bourse.montant as amount" +
            " from Member m join ReRegistration rr on m.id = rr.reRegistrationKey.member " +
            "join MemberHasBourse mhb on mhb.memberHasBourseKey.memberId = m.id where" +
            " mhb.bourse.lebelle!='non boursier' and rr.reRegistrationKey.yearOfRegistration =:idYear and m.id not in " +
            "(select c.contributionKey.memberId " +
                "from Contribution c where c.contributionKey.idMonth=:idMonth and c.contributionKey.idYear= :idYear ) "
    )
    public List<MemberThatContributeDto> findAllNotContributeForAMonthOfAYear(@Param("idMonth") Long idMonth, @Param("idYear") Long idYear);

    @Query("select c from Contribution  c where c.contributionKey.memberId= :id")
    List<Contribution> findAllByMember(@Param("id") Long id);

     */
}
