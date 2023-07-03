package io.management.patient.exception;

public class ExpiredTokenException extends RuntimeException {
    public ExpiredTokenException(String s) {
        super(s);
    }
}
