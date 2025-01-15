package org.aemudapi.member.repository;

import org.aemudapi.member.entity.Member_Session_PK;
import org.aemudapi.member.entity.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactInfoRepository extends JpaRepository<ContactInfo, Member_Session_PK> {
}
