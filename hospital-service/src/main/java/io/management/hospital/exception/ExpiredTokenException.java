package io.management.hospital.exception;

public class ExpiredTokenException extends RuntimeException {
    public ExpiredTokenException(String s) {
        super(s);
    }
}
