package com.api.movie.exception;

public class MovieAlreadyException extends RuntimeException {
    public MovieAlreadyException(String message) {
        super(message);
    }
}
