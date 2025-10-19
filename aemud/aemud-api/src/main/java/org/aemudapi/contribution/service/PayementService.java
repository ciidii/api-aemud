package org.aemudapi.contribution.service;

import org.aemudapi.contribution.dto.PayementRequestDTO;

public interface PayementService {
    void addPayement(PayementRequestDTO payementRequest);
}
