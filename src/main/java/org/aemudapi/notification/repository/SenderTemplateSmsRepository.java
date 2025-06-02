package org.aemudapi.notification.repository;

import org.aemudapi.notification.entity.RecipientTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SenderTemplateSmsRepository extends JpaRepository<RecipientTemplate, String> {
}
