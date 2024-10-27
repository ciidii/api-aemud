package com.amud.io.aemudapi.repositories;

import com.amud.io.aemudapi.entities.ContactInfo;
import com.amud.io.aemudapi.entities.MemberAndYearKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactInfoRepository extends JpaRepository<ContactInfo, MemberAndYearKey> {
}
