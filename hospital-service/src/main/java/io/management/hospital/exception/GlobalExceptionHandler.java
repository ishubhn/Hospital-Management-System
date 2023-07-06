package io.management.hospital.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(HospitalAlreadyPresentException.class)
    public ResponseEntity<ErrorMessage> handleHospitalAlreadyPresentException
            (HospitalAlreadyPresentException exception, WebRequest request) {

        ErrorMessage errorMessage = new ErrorMessage(LocalDateTime.now(), exception.getMessage(),
                request.getDescription(true));

        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoSuchHospitalExistException.class)
    public ResponseEntity<ErrorMessage> handleNoSuchHospitalExistException
            (HospitalAlreadyPresentException exception, WebRequest request) {

        ErrorMessage errorMessage = new ErrorMessage(LocalDateTime.now(), exception.getMessage(),
                request.getDescription(true));

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchDoctorEntityException.class)
    public ResponseEntity<ErrorMessage> handleNoSuchDoctorEntityException
            (NoSuchDoctorEntityException exception, WebRequest request) {

        ErrorMessage errorMessage = new ErrorMessage(LocalDateTime.now(), exception.getMessage(),
                request.getDescription(true));

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchOwnerEntityException.class)
    public ResponseEntity<ErrorMessage> handleNoSuchOwnerEntityException
            (NoSuchOwnerEntityException exception, WebRequest request) {

        ErrorMessage errorMessage = new ErrorMessage(LocalDateTime.now(), exception.getMessage(),
                request.getDescription(true));

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    // Exception to handle Feign Exception
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorMessage> handleFeignException
    (FeignException exception, WebRequest request) {

        ErrorMessage errorMessage = new ErrorMessage(LocalDateTime.now(), exception.getMessage(),
                request.getDescription(true));

        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidAuthorizationTokenException.class)
    public ResponseEntity<ErrorMessage> handleInvalidAuthorizationTokenException
            (InvalidAuthorizationTokenException exception, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(LocalDateTime.now(), exception.getMessage(),
                request.getDescription(true));

        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ExpiredTokenException.class)
    public ResponseEntity<ErrorMessage> handleExpiredTokenException
            (ExpiredTokenException exception, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(LocalDateTime.now(), exception.getMessage(),
                request.getDescription(true));

        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }
}
