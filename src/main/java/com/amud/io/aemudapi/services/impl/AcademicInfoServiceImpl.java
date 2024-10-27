package com.amud.io.aemudapi.services.impl;

import com.amud.io.aemudapi.dto.AcademicInfoRequestDTO;
import com.amud.io.aemudapi.dto.AddressInfoRequestDto;
import com.amud.io.aemudapi.entities.AcademicInfo;
import com.amud.io.aemudapi.entities.AddressInfo;
import com.amud.io.aemudapi.entities.MemberAndYearKey;
import com.amud.io.aemudapi.entities.YearOfSession;
import com.amud.io.aemudapi.mappers.AcademicInfoMapper;
import com.amud.io.aemudapi.repositories.AcademicInfoRepository;
import com.amud.io.aemudapi.repositories.YearOfSessionRepository;
import com.amud.io.aemudapi.services.AcademicInfoService;
import com.amud.io.aemudapi.services.YearOfSessionServices;
import com.amud.io.aemudapi.utils.ResponseVO;
import com.amud.io.aemudapi.utils.ResponseVOBuilder;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
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
        AcademicInfo academicInfoDB = this.academicInfoRepository.save(academicInfo);
        ResponseVO<Void> responseVO = new ResponseVOBuilder<Void>().success().build();
        return new ResponseEntity<>(responseVO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseVO<AcademicInfoRequestDTO>> getCurrentSessionMemberAddress(Long memberID) {
        YearOfSession yearOfSession = this.yearOfSessionRepository.findCurrentSession();
        MemberAndYearKey memberAndYearKey = new MemberAndYearKey(yearOfSession.getIdYear(), memberID);
        AcademicInfo addressInfo = this.academicInfoRepository.findById(memberAndYearKey).orElseThrow(() -> new EntityNotFoundException("C'est utilisateur ne s'Inscrit cette année"));
        AcademicInfoRequestDTO infoRequestDto = this.academicInfoMapper.toDto(addressInfo);
        ResponseVO<AcademicInfoRequestDTO> responseVO = new ResponseVOBuilder<AcademicInfoRequestDTO>().addData(infoRequestDto).build();
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }
}
