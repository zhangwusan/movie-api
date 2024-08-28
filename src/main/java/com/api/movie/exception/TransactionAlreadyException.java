package com.api.movie.exception;

public class TransactionAlreadyException extends RuntimeException {
    public TransactionAlreadyException(String message) {
        super(message);
    }

}
