package io.service.auth.exception;

public class AuthenticationTokenExpiredException extends RuntimeException {

    public AuthenticationTokenExpiredException(String s) {
        super(s);
    }

    public AuthenticationTokenExpiredException(String errorOccurredWhileValidatingToken, Exception e) {
        super(errorOccurredWhileValidatingToken, e);
    }
}
