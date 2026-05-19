package com.bank.repository;

import com.bank.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findByFromAccountOrToAccount(String from, String to);
}