package com.amud.io.aemudapi.repositories;

import com.amud.io.aemudapi.entities.Bourse;
import com.amud.io.aemudapi.entities.MemberHasBourse;
import com.amud.io.aemudapi.entities.MemberHasBourseKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberHasBourseRepository extends JpaRepository<MemberHasBourse,MemberHasBourseKey> {
    /*
    @Query("select m from MemberHasBourse m where m.memberHasBourseKey.memberId= :member")
        List<MemberHasBourse> findAllByMemberId(@Param("member" )Long member);

    @Query("SELECT mhb.bourse from MemberHasBourse mhb where mhb.memberHasBourseKey.memberId= :memberId and mhb.memberHasBourseKey.year_ =:idYear")
    Bourse findByBourseByIdYearAndIdMember(@Param("memberId")Long memberId, @Param("idYear") Long idYear);

     */
}
