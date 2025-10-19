package org.aemudapi.notification.repository;

import org.aemudapi.notification.entity.SmsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsModelRepository extends JpaRepository<SmsModel, String> {
    SmsModel getSmsModelById(String id);
}
