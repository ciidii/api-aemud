package org.aemudapi.exceptions.customeExceptions;

public class ActiveMandateNotFoundException extends RuntimeException {
    public ActiveMandateNotFoundException(String message) {
        super(message);
    }
}
