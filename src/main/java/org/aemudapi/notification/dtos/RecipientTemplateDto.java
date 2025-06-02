package org.aemudapi.notification.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipientTemplateDto {
    private String id;
    private String[] recipient;
    private String templateName;
}
