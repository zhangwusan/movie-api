package com.api.movie.exception;

public class GenreAlreadyException extends RuntimeException {
    public GenreAlreadyException(String message) {
        super(message);
    }
}
