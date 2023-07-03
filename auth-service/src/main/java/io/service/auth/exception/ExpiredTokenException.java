package io.service.auth.exception;

public class ExpiredTokenException extends RuntimeException {
    public ExpiredTokenException(String s) {
        super(s);
    }
}
