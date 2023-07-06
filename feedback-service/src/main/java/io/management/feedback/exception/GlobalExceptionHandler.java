package io.management.feedback.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RatingAlreadyExistException.class)
    public ResponseEntity<ErrorMessage> handleRatingAlreadyExistException(RatingAlreadyExistException exception,
                                                                          WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(LocalDateTime.now(), exception.getMessage(),
                request.getDescription(true));

        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoSuchRatingException.class)
    public ResponseEntity<ErrorMessage> handleNoSuchRatingException(NoSuchRatingException exception,
                                                                    WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(LocalDateTime.now(), exception.getMessage(),
                request.getDescription(true));

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleUserNotFoundException(UsernameNotFoundException exception,
                                                                    WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(LocalDateTime.now(), exception.getMessage(),
                request.getDescription(true));

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExpiredTokenException.class)
    public ResponseEntity<ErrorMessage> handleExpiredTokenException
            (ExpiredTokenException exception, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(LocalDateTime.now(), exception.getMessage(),
                request.getDescription(true));

        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }
}
