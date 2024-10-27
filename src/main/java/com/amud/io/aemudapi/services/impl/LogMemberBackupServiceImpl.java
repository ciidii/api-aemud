package com.amud.io.aemudapi.services.impl;

import com.amud.io.aemudapi.config.AppConfig;
import com.amud.io.aemudapi.dto.MemberRequestDto;
import com.amud.io.aemudapi.entities.LogMemberBackup;
import com.amud.io.aemudapi.mappers.MemberMapper;
import com.amud.io.aemudapi.repositories.LogMemberBackupRepository;
import com.amud.io.aemudapi.services.GoogleSheetsService;
import com.amud.io.aemudapi.services.LogMemberBackupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LogMemberBackupServiceImpl implements LogMemberBackupService {
    private final LogMemberBackupRepository logMemberBackupRepository;
    private final MemberMapper memberMapper;
    private final GoogleSheetsService googleSheetsService;
    private final AppConfig appConfig;

    public LogMemberBackupServiceImpl(LogMemberBackupRepository logMemberBackupRepository, MemberMapper memberMapper, GoogleSheetsService googleSheetsService, AppConfig appConfig) {
        this.logMemberBackupRepository = logMemberBackupRepository;
        this.memberMapper = memberMapper;
        this.googleSheetsService = googleSheetsService;
        this.appConfig = appConfig;
    }

    @Override
    public ResponseEntity<String> backup() throws GeneralSecurityException, IOException {
        List<MemberRequestDto> memberRequestDtos = new ArrayList<>();
        List<LogMemberBackup> logMemberBackups = logMemberBackupRepository.findAllWhereBackupIsFalse().orElseThrow();
        List<LogMemberBackup> logMemberSaved = new ArrayList<>();
        if (!logMemberBackups.isEmpty()) {
            logMemberBackups.forEach(memberBackup -> {
                memberRequestDtos.add(memberMapper.toDto(memberBackup.getMember()));
                memberBackup.setBackup(true);
                logMemberSaved.add(memberBackup);
            });
            googleSheetsService.appendMemberDataToSheet(appConfig.getSpreadID(), memberRequestDtos);
            logMemberBackupRepository.saveAll(logMemberSaved);
            return new ResponseEntity<>("Sauvegarde effectuer avec succèe", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Aucun donnée à souvegarder", HttpStatus.NOT_ACCEPTABLE);
    }
}
