package org.aemudapi.contribution.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.aemudapi.contribution.dto.MonthDTO;
import org.aemudapi.contribution.entity.Month;
import org.aemudapi.contribution.mapper.MonthMapper;
import org.aemudapi.contribution.repository.MonthRepository;
import org.aemudapi.contribution.service.MonthService;
import org.aemudapi.utils.ResponseVO;
import org.aemudapi.utils.ResponseVOBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MonthServiceImpl implements MonthService {
    private final MonthRepository monthRepository;
    private final MonthMapper monthMapper;

    @Override
    public ResponseEntity<ResponseVO<Void>> addMonth(MonthDTO monthDTO) {
        Month month = this.monthMapper.toEntity(monthDTO);
        this.monthRepository.save(month);
        return new ResponseEntity<>(new ResponseVOBuilder<Void>().success().build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<Void>> addMonth(List<MonthDTO> month) {
        List<Month> months = this.monthMapper.toEntityList(month);
        this.monthRepository.saveAll(months);
        return new ResponseEntity<>(new ResponseVOBuilder<Void>().success().build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<Void>> updateMonth(MonthDTO month) {

        return this.addMonth(month);
    }

    @Override
    public ResponseEntity<ResponseVO<Void>> deleteMonth(MonthDTO month) {
        Month month1 = this.monthRepository.findById(month.getId()).orElseThrow(() -> new EntityNotFoundException("Month not found"));
        this.monthRepository.delete(month1);
        return new ResponseEntity<>(new ResponseVOBuilder<Void>().success().build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<List<MonthDTO>>> getAllMonth() {
        List<Month> months = this.monthRepository.findAll();
        List<MonthDTO> monthDTOs = this.monthMapper.toDTOList(months);
        return new ResponseEntity<>(new ResponseVOBuilder<List<MonthDTO>>().addData(monthDTOs).build(), HttpStatus.OK);
    }

}
