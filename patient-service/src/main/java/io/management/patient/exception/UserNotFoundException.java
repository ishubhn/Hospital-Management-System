package io.management.patient.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String errMessage) {
        super(errMessage);
    }

    public UserNotFoundException(String errMessage, Throwable cause) {
        super(errMessage, cause);
    }
}
