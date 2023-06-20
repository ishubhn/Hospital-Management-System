package io.management.pharmacy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoSuchMedicineExistException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchMedicineExistException(
            NoSuchMedicineExistException exception, WebRequest request) {
        ErrorResponse errorMessage = new ErrorResponse(LocalDateTime.now(), exception.getMessage(),
                request.getDescription(true));

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
