package pl.equipRental.exception;

import pl.equipRental.exception.GlobalExceptionHandler;

public class DuplicatePeselException extends RuntimeException {


    public DuplicatePeselException(String message) {
        super(message);
    }
}
