package com.api.movie.dtos.mapping;

import java.math.BigDecimal;

import com.api.movie.dtos.request.TransactionRequest;
import com.api.movie.dtos.response.TransactionResponse;
import com.api.movie.models.Subscription;
import com.api.movie.models.Transaction;
import com.api.movie.models.User;

public class TransactionMapper {

    public Transaction toTransaction(TransactionRequest request, User user, Subscription subscription) {
        return new Transaction(
                null,
                user,
                subscription,
                BigDecimal.valueOf(request.amount()),
                request.paymentMethod(),
                request.status(),
                null,
                null,
                null);
    }

    public static Transaction toTransaction(TransactionRequest request) {
        return new Transaction(
                null,
                null,
                null,
                BigDecimal.valueOf(request.amount()),
                request.paymentMethod(),
                request.status(),
                null,
                null,
                null);
    }

    public static TransactionResponse toTransactionResponse(Transaction transaction) {
        return new TransactionResponse(
                transaction.getId(),
                transaction.getUser(),
                transaction.getSubscription(),
                transaction.getAmount(),
                transaction.getStatus(),
                transaction.getPaymentMethod(),
                transaction.getTransactionDate(),
                transaction.getCreatedAt(),
                transaction.getUpdatedAt());
    }
}
