package org.aemudapi.member.repository;

import org.aemudapi.member.entity.Member_Session_PK;
import org.aemudapi.member.entity.AddressInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressInfoRepository extends JpaRepository<AddressInfo, Member_Session_PK> {
    @Query("select af from AcademicInfo af where af.member.id= :memberID and af.year.currentYear is true")
    AddressInfo findByMemberAndSession(Long memberID);
}
