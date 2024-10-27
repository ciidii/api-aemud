package com.amud.io.aemudapi.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Service
public interface LogMemberBackupService {
    public ResponseEntity<String> backup() throws GeneralSecurityException, IOException;
}
