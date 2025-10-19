package org.aemudapi.exceptions.customeExceptions;

public class ContributionAlreadyExistsException extends RuntimeException {
    public ContributionAlreadyExistsException(String message) {
        super(message);
    }
}