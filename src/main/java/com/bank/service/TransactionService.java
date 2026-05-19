package com.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bank.model.Transaction;
import com.bank.repository.TransactionRepository;

@Service
public class TransactionService {

    private final TransactionRepository repo;

    public TransactionService(TransactionRepository repo) {
        this.repo = repo;
    }

    public Transaction save(Transaction t) {
        return repo.save(t);
    }

    public List<Transaction> getHistory(String accountId) {
        return repo.findByFromAccountOrToAccount(accountId, accountId);
    }
}