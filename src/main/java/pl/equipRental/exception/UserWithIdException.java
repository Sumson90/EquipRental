package pl.equipRental.exception;

public class UserWithIdException extends RuntimeException {
    public UserWithIdException(String message) {
        super(message);
    }
}

