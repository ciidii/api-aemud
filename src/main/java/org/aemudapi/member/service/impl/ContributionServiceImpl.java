package org.aemudapi.member.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.aemudapi.contribution.controller.ContributionKeyDto;
import org.aemudapi.contribution.controller.ContributionResponseDto;
import org.aemudapi.contribution.entity.MonthContribution;
import org.aemudapi.member.entity.YearOfSession;
import org.aemudapi.exceptions.customeExceptions.ContributionAlreadyExistsException;
import org.aemudapi.contribution.mapper.ContributionKeyMapper;
import org.aemudapi.member.entity.Contribution;
import org.aemudapi.member.entity.ContributionKey;
import org.aemudapi.member.repository.MemberRepository;
import org.aemudapi.member.repository.YearOfSessionRepository;
import org.aemudapi.member.service.ContributionService;
import org.aemudapi.notification.repository.MonthContributionRepository;
import org.aemudapi.contribution.repository.ContributionRepository;
import org.aemudapi.utils.ResponseVO;
import org.aemudapi.utils.ResponseVOBuilder;
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
