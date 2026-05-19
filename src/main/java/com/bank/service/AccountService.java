package com.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bank.exception.AccountNotFoundException;
import com.bank.exception.InsufficientBalanceException;
import com.bank.model.Account;
import com.bank.repository.AccountRepository;

@Service
public class AccountService {

    private final AccountRepository repo;

    public AccountService(AccountRepository repo) {
        this.repo = repo;
    }

    public Account createAccount(String name) {
        return repo.save(new Account(name));
    }

    public Account get(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
    }

    public List<Account> getAll() {
        return repo.findAll();
    }

    public Account deposit(String id, double amount) {
        Account acc = get(id);
        acc.setBalance(acc.getBalance() + amount);
        return repo.save(acc);
    }

    public Account withdraw(String id, double amount) {
        Account acc = get(id);
        if (acc.getBalance() < amount)
            throw new InsufficientBalanceException("Not enough balance");

        acc.setBalance(acc.getBalance() - amount);
        return repo.save(acc);
    }
}