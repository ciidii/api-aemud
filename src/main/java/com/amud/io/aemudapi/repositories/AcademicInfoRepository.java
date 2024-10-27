package com.amud.io.aemudapi.repositories;

import com.amud.io.aemudapi.entities.AcademicInfo;
import com.amud.io.aemudapi.entities.MemberAndYearKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicInfoRepository extends JpaRepository<AcademicInfo, MemberAndYearKey> {
}
