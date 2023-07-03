package io.management.feedback.exception;

public class ExpiredTokenException extends RuntimeException {
    public ExpiredTokenException(String s) {
        super(s);
    }
}
