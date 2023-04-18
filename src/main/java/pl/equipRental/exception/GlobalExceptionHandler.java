package pl.equipRental.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {DuplicatePeselException.class})
    public ResponseEntity<Object> handleDuplicatePeselException(DuplicatePeselException ex) {

        HttpStatus status = HttpStatus.CONFLICT;
        String errorMessage = ex.getMessage();

        return new ResponseEntity<>(errorMessage, status);
    }

    @ExceptionHandler(value = {UserWithIdException.class})
    public ResponseEntity<Object> handleUserWithIdException(UserWithIdException ex) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        String errorMessage = ex.getMessage();

        return new ResponseEntity<>(errorMessage, status);
    }
}

