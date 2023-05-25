package pl.equipRental.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "To przypisanie już zostało zakończone")
public class AssignmentAlreadyFinishedException extends RuntimeException { }