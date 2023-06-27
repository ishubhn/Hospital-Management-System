package io.service.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class AuthExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorInfo> handleUserAlreadyExistException(UserAlreadyExistException ex, WebRequest request) {
        ErrorInfo errorInfo = new ErrorInfo(ex.getMessage(),
                request.getDescription(true));

        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorInfo> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        ErrorInfo errorInfo = new ErrorInfo(ex.getMessage(),
                request.getDescription(true));
        return new ResponseEntity<>(errorInfo, HttpStatus.UNAUTHORIZED);
    }
}
