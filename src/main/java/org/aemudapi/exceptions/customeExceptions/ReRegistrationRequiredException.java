package org.aemudapi.exceptions.customeExceptions;

public class ReRegistrationRequiredException extends RuntimeException {

    public ReRegistrationRequiredException(String message) {
        super(message);
    }

}