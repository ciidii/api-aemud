package com.amud.io.aemudapi.repositories;

import com.amud.io.aemudapi.entities.AddressInfo;
import com.amud.io.aemudapi.entities.Member;
import com.amud.io.aemudapi.entities.MemberAndYearKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressInfoRepository extends JpaRepository<AddressInfo, MemberAndYearKey> {
    @Query("select af from AcademicInfo af where af.member.id= :memberID and af.year.currentYear is true")
    AddressInfo findByMemberAndSession(Long memberID);
}
