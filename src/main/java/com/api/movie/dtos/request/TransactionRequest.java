package com.api.movie.dtos.request;

import java.util.UUID;

public record TransactionRequest(
        UUID userId,
        Long subscriptionId,
        Double amount,
        String status,
        String paymentMethod) {
}
