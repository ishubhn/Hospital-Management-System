package io.management.pharmacy.exceptions;

public class InvalidAuthorizationTokenException extends RuntimeException {
    public InvalidAuthorizationTokenException(String s) {
        super(s);
    }
}
