package io.management.hospital.exception;

public class InvalidAuthorizationTokenException extends RuntimeException {
    public InvalidAuthorizationTokenException(String s) {
        super(s);
    }
}
