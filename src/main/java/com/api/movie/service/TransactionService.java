package com.api.movie.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.movie.models.Transaction;

@Service
public interface TransactionService {
    public Transaction getTransactionById(Long id);
    public Transaction createTransaction(Transaction transaction);
    public Transaction updateTransactionById(Long id, Transaction transaction);
    public boolean deleteTransactionById(Long id);
    public List<Transaction> getAllTransactions();

}
