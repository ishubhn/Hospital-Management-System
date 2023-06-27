package io.service.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class AuthExceptionHandler extends ResponseEntityExceptionHandler {
    public ResponseEntity<ErrorInfo> handleUserAlreadyExistException(UserAlreadyExistException e) {
        return new ResponseEntity<>(new ErrorInfo("ILLEGAL_ARGUMENT", "Username already exists"),
                HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ErrorInfo> handleBadCredentialsException(BadCredentialsException ex) {
        return new ResponseEntity<>(new ErrorInfo("BAD_CREDENTIALS", "Invalid Username or Password"),
                HttpStatus.UNAUTHORIZED);
    }
}
