package org.aemudapi.member.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.aemudapi.member.dtos.AcademicInfoRequestDTO;
import org.aemudapi.member.entity.Member_Session_PK;
import org.aemudapi.member.entity.Session;
import org.aemudapi.exceptions.customeExceptions.NoActiveSection;
import org.aemudapi.member.entity.AcademicInfo;
import org.aemudapi.member.mapper.AcademicInfoMapper;
import org.aemudapi.member.repository.AcademicInfoRepository;
import org.aemudapi.member.repository.YearOfSessionRepository;
import org.aemudapi.member.service.AcademicInfoService;
import org.aemudapi.utils.ResponseVO;
import org.aemudapi.utils.ResponseVOBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AcademicInfoServiceImpl implements AcademicInfoService {
    private final AcademicInfoMapper academicInfoMapper;
    private final AcademicInfoRepository academicInfoRepository;
    private final YearOfSessionRepository yearOfSessionRepository;

    @Override
    public ResponseEntity<ResponseVO<Void>> createAcademicInfo(AcademicInfoRequestDTO academicInfoRequestDTO) {
        AcademicInfo academicInfo = this.academicInfoMapper.toEntity(academicInfoRequestDTO);
        this.academicInfoRepository.save(academicInfo);
        ResponseVO<Void> responseVO = new ResponseVOBuilder<Void>().success().build();
        return new ResponseEntity<>(responseVO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseVO<AcademicInfoRequestDTO>> getCurrentSessionMemberAcademicInfo(Long memberID) {
        Session session = this.yearOfSessionRepository.findCurrentSession().orElseThrow(() -> new NoActiveSection("No active session"));
        Member_Session_PK memberSessionPK = new Member_Session_PK(session.getIdYear(), memberID);
        AcademicInfo addressInfo = this.academicInfoRepository.findById(memberSessionPK).orElseThrow(() -> new EntityNotFoundException("C'est utilisateur ne s'Inscrit cette année"));
        AcademicInfoRequestDTO infoRequestDto = this.academicInfoMapper.toDto(addressInfo);
        ResponseVO<AcademicInfoRequestDTO> responseVO = new ResponseVOBuilder<AcademicInfoRequestDTO>().addData(infoRequestDto).build();
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }
}
