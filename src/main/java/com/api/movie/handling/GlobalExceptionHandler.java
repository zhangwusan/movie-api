package com.api.movie.handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.api.movie.exception.GenreAlreadyException;
import com.api.movie.exception.GenreNotFoundException;
import com.api.movie.exception.UserAlreadyExistsException;
import com.api.movie.exception.UserNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerGlobalException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handlerUserAlreadyExistsException(UserAlreadyExistsException exception) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT, exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerUserNotFoundException(UserNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GenreNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerGenreNotFoundException(GenreNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GenreAlreadyException.class)
    public ResponseEntity<ErrorResponse> handleGenreAlreadyException(GenreNotFoundException exception) {
        ErrorResponse error = new ErrorResponse(HttpStatus.CONFLICT, exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

}
