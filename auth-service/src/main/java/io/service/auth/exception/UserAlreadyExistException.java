package io.service.auth.exception;

public class UserAlreadyExistException extends Throwable {
    public UserAlreadyExistException(String msg) {
        super(msg);
    }
}
