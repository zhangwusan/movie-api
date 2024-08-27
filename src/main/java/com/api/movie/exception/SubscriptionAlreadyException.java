package com.api.movie.exception;

public class SubscriptionAlreadyException extends RuntimeException {
    public SubscriptionAlreadyException(String message) {
        super(message);
    }
}
