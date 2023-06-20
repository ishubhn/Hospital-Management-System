package io.management.patient.exception;

public class UserAlreadyPresentException extends RuntimeException {
    public UserAlreadyPresentException(String errMessage) {
        super(errMessage);
    }
}
