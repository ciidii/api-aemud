package org.aemudapi.exceptions.customeExceptions;

public class CantDeletActiveSectionException extends RuntimeException {
    public CantDeletActiveSectionException(String message) {
        super(message);
    }
}
