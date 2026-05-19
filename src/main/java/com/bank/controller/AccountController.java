package com.bank.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.model.TransactionType;
import com.bank.service.AccountService;
import com.bank.service.TransactionService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;
    private final TransactionService transactionService;

    public AccountController(AccountService a, TransactionService t) {
        this.accountService = a;
        this.transactionService = t;
    }

    @PostMapping
    public Account create(@RequestParam String name) {
        return accountService.createAccount(name);
    }

    @GetMapping
    public List<Account> all() {
        return accountService.getAll();
    }

    @PostMapping("/{id}/deposit")
    public Account deposit(@PathVariable String id, @RequestParam double amount) {
        Account acc = accountService.deposit(id, amount);
        transactionService.save(new Transaction(id, null, amount, TransactionType.DEPOSIT));
        return acc;
    }

    @PostMapping("/{id}/withdraw")
    public Account withdraw(@PathVariable String id, @RequestParam double amount) {
        Account acc = accountService.withdraw(id, amount);
        transactionService.save(new Transaction(id, null, amount, TransactionType.WITHDRAW));
        return acc;
    }
}