package org.aemudapi.notification.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aemudapi.notification.entity.SmsModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsModelDTO {
    private String id;
    private String modelName;
    private String smsModel;
}
