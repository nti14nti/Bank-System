package com.bank.service;

import com.bank.model.Transaction;
import com.bank.model.TransactionType;
import com.bank.repository.TransactionRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {

    private TransactionRepository transactionRepository;
    private TransactionService transactionService;

    @BeforeEach
    void setup() {
        transactionRepository = mock(TransactionRepository.class);
        transactionService = new TransactionService(transactionRepository);
    }

    @Test
    void testSaveTransaction() {

        Transaction transaction =
                new Transaction("1", "2", 100, TransactionType.TRANSFER);

        when(transactionRepository.save(any(Transaction.class)))
                .thenReturn(transaction);

        Transaction saved = transactionService.save(transaction);

        assertEquals(100, saved.getAmount());
        assertEquals(TransactionType.TRANSFER, saved.getType());
    }

    @Test
    void testGetHistory() {

        List<Transaction> transactions = List.of(
                new Transaction("1", "2", 100, TransactionType.TRANSFER),
                new Transaction("1", null, 50, TransactionType.DEPOSIT)
        );

        when(transactionRepository
                .findByFromAccountOrToAccount("1", "1"))
                .thenReturn(transactions);

        List<Transaction> result =
                transactionService.getHistory("1");

        assertEquals(2, result.size());
    }
}