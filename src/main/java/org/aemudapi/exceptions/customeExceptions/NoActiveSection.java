package org.aemudapi.exceptions.customeExceptions;

public class NoActiveSection extends RuntimeException {
    public NoActiveSection(String message) {
        super(message);
    }
}
