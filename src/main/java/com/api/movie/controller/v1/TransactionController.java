package com.api.movie.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.movie.dtos.mapping.TransactionMapper;
import com.api.movie.dtos.request.TransactionRequest;
import com.api.movie.dtos.response.TransactionResponse;
import com.api.movie.models.Subscription;
import com.api.movie.models.Transaction;
import com.api.movie.models.User;
import com.api.movie.service.SubscriptionService;
import com.api.movie.service.TransactionService;
import com.api.movie.service.UserService;
import com.api.movie.utils.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    @Autowired
    private TransactionService service;
    @Autowired
    private UserService userService;
    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<TransactionResponse>>> getAllTransactions() {
        List<Transaction> transactions = service.getAllTransactions();
        List<TransactionResponse> response = transactions.stream()
                .map(TransactionMapper::toTransactionResponse)
                .toList();
        return ResponseEntity.ok(new ApiResponse<>(response, "Transactions retrieved successfully", HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TransactionResponse>> createTransaction(@RequestBody TransactionRequest request) {
        User user = userService.getUserById(request.userId());
        Subscription subscription = subscriptionService.getSubscriptionById(request.subscriptionId());
        Transaction transaction = service
                .createTransaction(new TransactionMapper().toTransaction(request, user, subscription));
        return ResponseEntity.ok(new ApiResponse<>(TransactionMapper.toTransactionResponse(transaction),
                "Transaction has been created", HttpStatus.CREATED));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TransactionResponse>> getTransactionById(@PathVariable Long id) {
        Transaction transaction = service.getTransactionById(id);
        return ResponseEntity
                .ok(new ApiResponse<>(TransactionMapper.toTransactionResponse(transaction), "success", HttpStatus.OK));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TransactionResponse>> updateTransaction(@PathVariable Long id,
            @RequestBody TransactionRequest request) {

        TransactionResponse response = TransactionMapper
                .toTransactionResponse(service.updateTransactionById(id, TransactionMapper.toTransaction(request)));
        return ResponseEntity.ok(new ApiResponse<>(response, "transaction updated successfully", HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteTransaction(@PathVariable Long id) {
        if (service.deleteTransactionById(id)) {
            return ResponseEntity.ok(new ApiResponse<>("Transaction was deleted successfully."));
        }
        return null;
    }
}
