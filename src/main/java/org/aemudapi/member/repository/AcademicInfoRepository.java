package org.aemudapi.member.repository;

import org.aemudapi.member.entity.MemberAndYearKey;
import org.aemudapi.member.entity.AcademicInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicInfoRepository extends JpaRepository<AcademicInfo, MemberAndYearKey> {
}
