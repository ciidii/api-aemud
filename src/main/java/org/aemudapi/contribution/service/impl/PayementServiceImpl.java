package org.aemudapi.contribution.service.impl;

import lombok.AllArgsConstructor;
import org.aemudapi.contribution.dto.PayementRequestDTO;
import org.aemudapi.contribution.entity.Payement;
import org.aemudapi.contribution.mapper.PayementMapper;
import org.aemudapi.contribution.repository.PayementRepository;
import org.aemudapi.contribution.service.PayementService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PayementServiceImpl implements PayementService {
    private final PayementRepository payementRepository;
    private final PayementMapper payementMapper;

    @Override
    public void addPayement(PayementRequestDTO payementRequest) {
        Payement payement = this.payementMapper.toEntity(payementRequest);
        this.payementRepository.save(payement);
    }
}
