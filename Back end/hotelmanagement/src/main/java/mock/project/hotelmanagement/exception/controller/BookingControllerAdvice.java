package mock.project.hotelmanagement.exception.controller;

import mock.project.hotelmanagement.dto.DataResponse;
import mock.project.hotelmanagement.exception.PresentOrFuture;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;

@RestControllerAdvice
public class BookingControllerAdvice {
    @ExceptionHandler(PresentOrFuture.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DataResponse handlePresentOrFuture(PresentOrFuture presentOrFuture) {
        return new DataResponse("Timeline Error !",presentOrFuture.getMessage(),null, 400);
    }

    @ExceptionHandler(DateTimeParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DataResponse handlePresentOrFuture(DateTimeParseException dateTimeParseException) {
        return new DataResponse("Timeline Error !",dateTimeParseException.getMessage(),null, 400);
    }
}
