package org.aemudapi.exceptions.customeExceptions;

public class EntityCannotBeDeletedException extends RuntimeException {
    public EntityCannotBeDeletedException(String message) {
        super(message);
    }
}
