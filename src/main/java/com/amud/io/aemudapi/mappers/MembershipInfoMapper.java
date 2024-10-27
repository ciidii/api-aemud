package com.amud.io.aemudapi.mappers;

import com.amud.io.aemudapi.dto.MembershipInfoDTO;
import com.amud.io.aemudapi.entities.*;
import com.amud.io.aemudapi.repositories.BourseRepository;
import com.amud.io.aemudapi.repositories.ClubRepository;
import com.amud.io.aemudapi.repositories.CommissionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MembershipInfoMapper {
    private final ClubRepository clubRepository;
    private final CommissionRepository commissionRepository;
    private final BourseRepository bourseRepository;

    public MembershipInfoMapper(ClubRepository clubRepository, CommissionRepository commissionRepository, BourseRepository bourseRepository) {
        this.clubRepository = clubRepository;
        this.commissionRepository = commissionRepository;
        this.bourseRepository = bourseRepository;
    }

    // Méthode pour mapper MembershipInfoDTO vers MembershipInfo
    public MembershipInfo toEntity(MembershipInfoDTO dto) {
        if (dto == null) {
            return null;
        }
        MembershipInfo entity = new MembershipInfo();
        YearOfSession yearOfSession = new YearOfSession();
        yearOfSession.setIdYear(dto.getYearOfMembership());
        entity.setYearOfMembership(yearOfSession);
        entity.setLegacyInstitution(dto.getLegacyInstitution());
        entity.setYearOfBac(dto.getYearOfBac());
        entity.setBacMention(dto.getBacMention());
        entity.setBacSeries(dto.getBacSeries());
        entity.setPay(dto.isPay());
        entity.setAemudCourses(dto.getAemudCourses());
        entity.setOtherCourses(dto.getOtherCourses());
        entity.setParticipatedActivity(dto.getParticipatedActivity());
        entity.setPoliticOrganisation(dto.getPoliticOrganisation());
        Bourse bourse = bourseRepository.findById(dto.getBourse()).orElseThrow(() -> new EntityNotFoundException("Pas de bourse avec cette identifiant"));
        entity.setBourse(bourse);
        entity.setCommission(getCommission(dto.getCommission()));
        List<Club> clubs = getClubs(dto.getClubs());
        entity.setClubs(clubs);
        return entity;
    }

    // Méthode pour mapper MembershipInfo vers MembershipInfoDTO
    public MembershipInfoDTO toDto(MembershipInfo entity) {
        if (entity == null) {
            return null;
        }

        MembershipInfoDTO dto = new MembershipInfoDTO();
        dto.setYearOfMembership(entity.getYearOfMembership().getIdYear());
        dto.setPay(entity.isPay());
        dto.setAemudCourses(entity.getAemudCourses());
        dto.setOtherCourses(entity.getOtherCourses());
        dto.setParticipatedActivity(entity.getParticipatedActivity());
        dto.setPoliticOrganisation(entity.getPoliticOrganisation());
        dto.setLegacyInstitution(entity.getLegacyInstitution());
        entity.setYearOfBac(entity.getYearOfBac());
        entity.setBacMention(entity.getBacMention());
        entity.setBacSeries(entity.getBacSeries());
        return dto;
    }

    private List<Long> getMemberClubIds(MembershipInfo member) {
        List<Long> ids = new ArrayList<>();
        member.getClubs().forEach((mem) -> {
            ids.add(mem.getId());
        });
        return ids;
    }

    private Commission getCommission(Long commissionID) {
        return this.commissionRepository.findById(commissionID).orElseThrow(() -> new EntityNotFoundException("Aucun commission trouver avec l'ID" + commissionID));
    }

    private List<Club> getClubs(Long clubID) {
        List<Club> clubs = new ArrayList<>();
        clubs.add(this.clubRepository.findById(clubID).orElseThrow(() -> new EntityNotFoundException("Pas de club avec l'ID" + clubID)));
        return clubs;
    }
}
