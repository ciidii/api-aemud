package org.aemudapi.exceptions;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.aemudapi.exceptions.customeExceptions.*;
import org.aemudapi.utils.ResponseErrorVo;
import org.aemudapi.utils.ResponseVO;
import org.aemudapi.utils.ResponseVOBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GeneralSecurityException.class)
    public ResponseEntity<ResponseVO<Void>> handleGeneralSecurityException(GeneralSecurityException ex) {
        ResponseErrorVo errorVo = new ResponseErrorVo("SECURITY_ERROR", "Erreur de sécurité", ex.getMessage());
        ResponseVO<Void> response = new ResponseVOBuilder<Void>().fail(HttpStatus.INTERNAL_SERVER_ERROR).error(errorVo, HttpStatus.INTERNAL_SERVER_ERROR).build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ResponseVO<Void>> handleIOException(IOException ex) {
        ResponseErrorVo errorVo = new ResponseErrorVo("IO_ERROR", "Erreur d'entrée/sortie", ex.getMessage());
        ResponseVO<Void> response = new ResponseVOBuilder<Void>().fail(HttpStatus.INTERNAL_SERVER_ERROR).error(errorVo, HttpStatus.INTERNAL_SERVER_ERROR).build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseVO<Void>> handleNotFoundException(EntityNotFoundException ex) {
        ResponseErrorVo errorVo = new ResponseErrorVo("ENTITY_NOT_FOUND", "L'entité introuvable", ex.getMessage());
        ResponseVO<Void> response = new ResponseVOBuilder<Void>().fail(HttpStatus.NOT_FOUND).error(errorVo, HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ResponseVO<Void>> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        ResponseErrorVo errorVo = new ResponseErrorVo("NOT_FOUND", "La ressource demandée est introuvable", ex.getMessage());
        ResponseVO<Void> response = new ResponseVOBuilder<Void>().fail(HttpStatus.NOT_FOUND).error(errorVo, HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseVO<Void>> handleGenericException(Exception ex) {
        ResponseErrorVo errorVo = new ResponseErrorVo("GENERIC_ERROR", "Erreur interne", ex.getMessage());
        ResponseVO<Void> response = new ResponseVOBuilder<Void>().fail(HttpStatus.INTERNAL_SERVER_ERROR).error(errorVo, HttpStatus.INTERNAL_SERVER_ERROR).build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ResponseVO<Void>> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        ResponseErrorVo errorVo = new ResponseErrorVo("USER_ALREADY_EXISTS", "L'utilisateur existe déjà", ex.getMessage());
        ResponseVO<Void> response = new ResponseVOBuilder<Void>().fail(HttpStatus.CONFLICT).error(errorVo, HttpStatus.CONFLICT).build();
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ReRegistrationRequiredException.class)
    public ResponseEntity<ResponseVO<Void>> handleReRegistrationRequiredException(ReRegistrationRequiredException ex) {
        ResponseErrorVo errorVo = new ResponseErrorVo("RE_REGISTRATION_REQUIRED", "Réinscription requise", ex.getMessage());
        ResponseVO<Void> response = new ResponseVOBuilder<Void>().fail(HttpStatus.PRECONDITION_REQUIRED).error(errorVo, HttpStatus.PRECONDITION_REQUIRED).build();
        return new ResponseEntity<>(response, HttpStatus.PRECONDITION_REQUIRED);
    }

    @ExceptionHandler(ContributionAlreadyExistsException.class)
    public ResponseEntity<ResponseVO<Void>> handleContributionAlreadyExistsException(ContributionAlreadyExistsException ex) {
        ResponseErrorVo errorVo = new ResponseErrorVo("CONTRIBUTION_ALREADY_EXISTS", "Cette membre a déjà cotisé", ex.getMessage());
        ResponseVO<Void> response = new ResponseVOBuilder<Void>().fail(HttpStatus.CONFLICT).error(errorVo, HttpStatus.CONFLICT).build();
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityCannotBeDeletedException.class)
    public ResponseEntity<ResponseVO<Void>> SessionCannotBeDeletedException(EntityCannotBeDeletedException ex) {
        ResponseErrorVo errorVo = new ResponseErrorVo("CANNOT_DELETE_ENTITY", ex.getMessage());
        ResponseVO<Void> response = new ResponseVOBuilder<Void>().fail(HttpStatus.CONFLICT).error(errorVo, HttpStatus.CONFLICT).build();
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MemberAllReadyRegisterException.class)
    public ResponseEntity<ResponseVO<Void>> MemberAllReadyRegisterException(MemberAllReadyRegisterException ex) {
        ResponseErrorVo errorVo = new ResponseErrorVo("MEMBER_ALL_READY_REGISTERED", ex.getMessage());
        ResponseVO<Void> response = new ResponseVOBuilder<Void>().fail(HttpStatus.CONFLICT).error(errorVo, HttpStatus.CONFLICT).build();
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<ResponseVO<Void>> EntityAllReadyException(EntityExistsException ex) {
        ResponseErrorVo errorVo = new ResponseErrorVo("ENTITY_ALL_READY_EXIST", ex.getMessage());
        ResponseVO<Void> response = new ResponseVOBuilder<Void>().fail(HttpStatus.CONFLICT).error(errorVo, HttpStatus.CONFLICT).build();
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
