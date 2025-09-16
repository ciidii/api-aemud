package org.aemudapi.contribution.mapper;

import lombok.AllArgsConstructor;
import org.aemudapi.contribution.dto.ContributionRequestDTO;
import org.aemudapi.contribution.dto.PayementRequestDTO;
import org.aemudapi.contribution.dto.PayementResponseDTO;
import org.aemudapi.contribution.entity.Contribution;
import org.aemudapi.contribution.entity.Payement;
import org.aemudapi.contribution.repository.ContributionRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PayementMapper {
    private final ContributionRepository contributionMapper;

    public Payement toEntity(PayementRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        List<Contribution> contributions = this.contributionMapper.findAllById(dto.contributions());
        Payement payement = new Payement();
        payement.setAmount(dto.amount());
        payement.setPay_methode(dto.pay_methode());
        payement.setContributions(contributions);
        return payement;
    }

    public PayementResponseDTO toDto(Payement entity) {
        if (entity == null) {
            return null;
        }
        return new PayementResponseDTO(entity.getAmount(), entity.getPaymentDate(), entity.getPay_methode());
    }
}
