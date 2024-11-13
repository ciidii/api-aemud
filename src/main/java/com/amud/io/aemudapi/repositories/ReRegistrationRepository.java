package com.amud.io.aemudapi.repositories;

import com.amud.io.aemudapi.entities.MemberAndYearKey;
import com.amud.io.aemudapi.entities.ReRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReRegistrationRepository extends JpaRepository<ReRegistration, MemberAndYearKey> {
}

