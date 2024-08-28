package com.api.movie.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.movie.exception.TransactionNotFoundException;
import com.api.movie.models.Transaction;
import com.api.movie.repositories.TransactionRepository;
import com.api.movie.service.TransactionService;

@Service
public class TransactionServiceImplement implements TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Override
    public Transaction getTransactionById(Long id) {
        return repository.findById(id).orElseThrow(() -> new TransactionNotFoundException("Transaction not found"));
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return repository.save(transaction);
    }

    @Override
    public Transaction updateTransactionById(Long id, Transaction transaction) {
        return repository.findById(id).map(transactionDoc -> {
            if(transaction.getUser() != null) {
                transactionDoc.setUser(transaction.getUser());
            }
            if(transaction.getSubscription() != null) {
                transactionDoc.setSubscription(transaction.getSubscription());
            }
            transactionDoc.setAmount(transaction.getAmount());
            transactionDoc.setStatus(transaction.getStatus());
            transactionDoc.setPaymentMethod(transaction.getPaymentMethod());
            return repository.save(transactionDoc);
        }).orElseThrow(() -> new TransactionNotFoundException("Transaction not found"));
    }

    @Override
    public boolean deleteTransactionById(Long id) {
        boolean isTransaction = repository.findById(id).isPresent();
        if (isTransaction) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }

}
