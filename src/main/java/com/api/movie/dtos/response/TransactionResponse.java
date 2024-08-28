package com.api.movie.dtos.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.api.movie.models.Subscription;
import com.api.movie.models.User;

public record TransactionResponse(
                Long id,
                User user,
                Subscription subscription,
                BigDecimal amount,
                String status,
                String paymentMethod,
                LocalDateTime transactionDate,
                LocalDateTime createdAt,
                LocalDateTime updatedAt) {

}
