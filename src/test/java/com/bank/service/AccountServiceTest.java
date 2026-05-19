package com.bank.service;

import com.bank.exception.InsufficientBalanceException;
import com.bank.model.Account;
import com.bank.repository.AccountRepository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AccountServiceTest {

    private AccountRepository accountRepository;
    private AccountService accountService;

    @BeforeEach
    void setup() {
        accountRepository = Mockito.mock(AccountRepository.class);
        accountService = new AccountService(accountRepository);
    }

    @Test
    void testCreateAccount() {

        Account account = new Account("Ali");

        when(accountRepository.save(any(Account.class)))
                .thenReturn(account);

        Account created = accountService.createAccount("Ali");

        assertEquals("Ali", created.getOwnerName());
    }

    @Test
    void testDeposit() {

        Account account = new Account("Sara");
        account.setId("1");
        account.setBalance(100);

        when(accountRepository.findById("1"))
                .thenReturn(Optional.of(account));

        when(accountRepository.save(any(Account.class)))
                .thenReturn(account);

        Account updated = accountService.deposit("1", 50);

        assertEquals(150, updated.getBalance());
    }

    @Test
    void testWithdraw() {

        Account account = new Account("Ahmed");
        account.setId("1");
        account.setBalance(200);

        when(accountRepository.findById("1"))
                .thenReturn(Optional.of(account));

        when(accountRepository.save(any(Account.class)))
                .thenReturn(account);

        Account updated = accountService.withdraw("1", 100);

        assertEquals(100, updated.getBalance());
    }

    @Test
    void testWithdrawInsufficientBalance() {

        Account account = new Account("Mona");
        account.setId("1");
        account.setBalance(50);

        when(accountRepository.findById("1"))
                .thenReturn(Optional.of(account));

        assertThrows(
                InsufficientBalanceException.class,
                () -> accountService.withdraw("1", 100)
        );
    }
}