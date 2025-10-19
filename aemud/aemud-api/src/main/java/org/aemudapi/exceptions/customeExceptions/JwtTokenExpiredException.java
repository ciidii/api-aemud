package org.aemudapi.exceptions.customeExceptions;


public class JwtTokenExpiredException extends RuntimeException {
    public JwtTokenExpiredException(String msg) {
        super(msg);
    }
}
