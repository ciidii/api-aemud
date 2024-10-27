package com.amud.io.aemudapi.controllers;

import com.amud.io.aemudapi.services.LogMemberBackupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequestMapping(path ="member")
public class LogMemberController {
    private final LogMemberBackupService logMemberBackupService;

    public LogMemberController(LogMemberBackupService logMemberBackupService) {
        this.logMemberBackupService = logMemberBackupService;
    }

    @PostMapping(path = "backup")
    public ResponseEntity<String> backup() throws GeneralSecurityException, IOException {
        return this.logMemberBackupService.backup();
    }
}
