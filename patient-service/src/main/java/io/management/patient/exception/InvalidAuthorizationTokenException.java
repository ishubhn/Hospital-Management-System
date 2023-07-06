package io.management.patient.exception;

public class InvalidAuthorizationTokenException extends RuntimeException {
    public InvalidAuthorizationTokenException(String s) {
        super(s);
    }
}
