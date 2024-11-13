package com.amud.io.aemudapi.services.impl;

import com.amud.io.aemudapi.dto.ContributionKeyDto;
import com.amud.io.aemudapi.dto.ContributionResponseDto;
import com.amud.io.aemudapi.entities.Contribution;
import com.amud.io.aemudapi.entities.ContributionKey;
import com.amud.io.aemudapi.entities.MonthContribution;
import com.amud.io.aemudapi.entities.YearOfSession;
import com.amud.io.aemudapi.exceptions.customeExceptions.ContributionAlreadyExistsException;
import com.amud.io.aemudapi.mappers.ContributionKeyMapper;
import com.amud.io.aemudapi.repositories.ContributionRepository;
import com.amud.io.aemudapi.repositories.MemberRepository;
import com.amud.io.aemudapi.repositories.MonthContributionRepository;
import com.amud.io.aemudapi.repositories.YearOfSessionRepository;
import com.amud.io.aemudapi.services.ContributionService;
import com.amud.io.aemudapi.utils.ResponseVO;
import com.amud.io.aemudapi.utils.ResponseVOBuilder;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class ContributionServiceImpl implements ContributionService {

    private final MemberRepository memberRepository;
    private final ContributionRepository contributionRepository;
    private final MonthContributionRepository monthContributionRepository;
    private final YearOfSessionRepository yearOfSessionRepository;
    private final ContributionKeyMapper contributionKeyMapper;


    public ContributionServiceImpl(
            MemberRepository memberRepository,
            ContributionRepository contributionRepository,
            MonthContributionRepository monthContributionRepository,
            YearOfSessionRepository yearOfSessionRepository,
            ContributionKeyMapper contributionKeyMapper
    ) {
        this.memberRepository = memberRepository;
        this.contributionRepository = contributionRepository;
        this.monthContributionRepository = monthContributionRepository;
        this.yearOfSessionRepository = yearOfSessionRepository;
        this.contributionKeyMapper = contributionKeyMapper;
    }

    public ResponseEntity<ResponseVO<ContributionResponseDto>> contribute(ContributionKeyDto contributionKeyDto) {
        // Bourse bourse = this.memberHasBourseRepository.findByBourseByIdYearAndIdMember(contributionKeyDto.getMemberId(),contributionKeyDto.getIdYear());
        ContributionKey contributionKey = this.contributionKeyMapper.toEntity(contributionKeyDto);
        if (!this.memberRepository.existsById(contributionKey.getMemberId())) {
            throw new EntityNotFoundException("Il faut inscrire un member avant qu'il cotise");
        }

        if (!this.yearOfSessionRepository.existsById(contributionKey.getIdYear())) {
            throw new EntityNotFoundException("Cette session n'est pas encors ouvert");
        }
        if (this.monthContributionRepository.existsById(contributionKey.getIdMonth())) {
            throw new EntityNotFoundException("Une cotisateur que les 12 mois de l'année");
        }

        if (this.contributionRepository.existsById(contributionKey)) {
            throw new ContributionAlreadyExistsException("cette member à déja cotiser pour se mois" + contributionKey);
        }

        Contribution contribution = new Contribution();
        contribution.setContributionKey(contributionKey);
        // contribution.setAmount(bourse.getMontant());
        contribution.setDateTime(LocalDateTime.now());
        contribution = this.contributionRepository.save(contribution);
        MonthContribution monthContribution = this.monthContributionRepository.findById(contributionKey.getIdMonth()).orElseThrow();
        YearOfSession yearOfSession = this.yearOfSessionRepository.findById(contributionKey.getIdYear()).orElseThrow();
        String month = monthContribution.getWording();
        Long year = (long) yearOfSession.getYear_();

        ContributionResponseDto contributionResponseDto = new ContributionResponseDto();

        contributionResponseDto.setMonth(month);
        contributionResponseDto.setIdYear(year);
        // contributionResponseDto.setAmount(bourse.getMontant());
        contributionResponseDto.setDateTime(contribution.getDateTime());

        ResponseVO<ContributionResponseDto> responseVO = new ResponseVOBuilder<ContributionResponseDto>().addData(contributionResponseDto).build();
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }
}
