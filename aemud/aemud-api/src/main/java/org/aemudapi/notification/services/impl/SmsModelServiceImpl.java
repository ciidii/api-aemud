package org.aemudapi.notification.services.impl;

import lombok.RequiredArgsConstructor;
import org.aemudapi.notification.dtos.SmsModelMapper;
import org.aemudapi.notification.dtos.SmsModelDTO;
import org.aemudapi.notification.entity.SmsModel;
import org.aemudapi.notification.repository.SmsModelRepository;
import org.aemudapi.notification.services.SmsModelService;
import org.aemudapi.utils.ResponseVO;
import org.aemudapi.utils.ResponseVOBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SmsModelServiceImpl implements SmsModelService {

    private final SmsModelRepository repository;
    private final SmsModelMapper smsModelMapper;

    @Override
    public ResponseEntity<ResponseVO<SmsModelDTO>> addSmsModel(SmsModelDTO dto) {

        SmsModel entity = this.smsModelMapper.toEntity(dto);
        SmsModel saved = repository.save(entity);
        SmsModelDTO smsModelDTO = smsModelMapper.toDto(saved);
        return new ResponseEntity<>(new ResponseVOBuilder<SmsModelDTO>().addData(smsModelDTO).build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO<List<SmsModelDTO>>> getAllSmsModels() {
        List<SmsModelDTO> models = repository.findAll().stream()
                .map(this.smsModelMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ResponseVOBuilder<List<SmsModelDTO>>().addData(models).build());
    }

    @Override
    public ResponseEntity<ResponseVO<Void>> deleteSmsModel(String id) {
        repository.deleteById(id);
        return ResponseEntity.ok(new ResponseVOBuilder<Void>().success().build());
    }

    @Override
    public ResponseEntity<ResponseVO<SmsModelDTO>> findSmsModelById(String id) {
        Optional<SmsModel> model = repository.findById(id);
        return model
                .map(s -> ResponseEntity.ok(new ResponseVOBuilder<SmsModelDTO>()
                        .addData(this.smsModelMapper.toDto(s))
                        .build()))
                .orElse(ResponseEntity.notFound().build());
    }

}
