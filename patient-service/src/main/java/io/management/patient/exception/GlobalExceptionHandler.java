package io.management.patient.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleUserNotFoundException
            (UserNotFoundException exception, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                exception.getMessage(), request.getDescription(true));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidAuthorizationTokenException.class)
    public ResponseEntity<ErrorDetails> handleInvalidAuthorizationTokenException
            (InvalidAuthorizationTokenException exception, WebRequest request) {
        ErrorDetails errorMessage = new ErrorDetails(LocalDateTime.now(), exception.getMessage(),
                request.getDescription(true));

        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ExpiredTokenException.class)
    public ResponseEntity<ErrorDetails> handleExpiredTokenException
            (ExpiredTokenException exception, WebRequest request) {
        ErrorDetails errorMessage = new ErrorDetails(LocalDateTime.now(), exception.getMessage(),
                request.getDescription(true));

        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }
}
