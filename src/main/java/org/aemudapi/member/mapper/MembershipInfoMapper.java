package org.aemudapi.member.mapper;

import org.aemudapi.club.repository.ClubRepository;
import org.aemudapi.member.dtos.MembershipInfoDTO;
import org.aemudapi.member.entity.MembershipInfo;
import org.aemudapi.member.entity.Session;
import org.aemudapi.member.repository.BourseRepository;
import org.aemudapi.member.repository.CommissionRepository;
import org.springframework.stereotype.Component;

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
        Session session = new Session();
        entity.setLegacyInstitution(dto.getLegacyInstitution());
        entity.setYearOfBac(dto.getYearOfBac());
        entity.setBacMention(dto.getBacMention());
        entity.setBacSeries(dto.getBacSeries());
      //  entity.setAemudCourses(dto.getAemudCourses());
      //  entity.setOtherCourses(dto.getOtherCourses());
        entity.setParticipatedActivity(dto.getParticipatedActivity());
        entity.setPoliticOrganisation(dto.getPoliticOrganisation());
        return entity;
    }

    // Méthode pour mapper MembershipInfo vers MembershipInfoDTO
    public MembershipInfoDTO toDto(MembershipInfo entity) {
        if (entity == null) {
            return null;
        }

        MembershipInfoDTO dto = new MembershipInfoDTO();
      //  dto.setAemudCourses(entity.getAemudCourses());
      //  dto.setOtherCourses(entity.getOtherCourses());
        dto.setBacSeries(entity.getBacSeries());
        dto.setBacMention(entity.getBacMention());
        dto.setYearOfBac(entity.getYearOfBac());
        dto.setParticipatedActivity(entity.getParticipatedActivity());
        dto.setPoliticOrganisation(entity.getPoliticOrganisation());
        dto.setLegacyInstitution(entity.getLegacyInstitution());
        entity.setYearOfBac(entity.getYearOfBac());
        entity.setBacMention(entity.getBacMention());
        entity.setBacSeries(entity.getBacSeries());
        return dto;
    }

//    private List<Long> getMemberClubIds(MembershipInfo member) {
//        List<Long> ids = new ArrayList<>();
//        member.getClubs().forEach((mem) -> {
//            ids.add(mem.getId());
//        });
//        return ids;
//    }

//    private Commission getCommission(Long commissionID) {
//        return this.commissionRepository.findById(commissionID).orElseThrow(() -> new EntityNotFoundException("Aucun commission trouver avec l'ID" + commissionID));
//    }
//
//    private List<Club> getClubs(Long clubID) {
//        List<Club> clubs = new ArrayList<>();
//        clubs.add(this.clubRepository.findById(clubID).orElseThrow(() -> new EntityNotFoundException("Pas de club avec l'ID" + clubID)));
//        return clubs;
//    }
}
