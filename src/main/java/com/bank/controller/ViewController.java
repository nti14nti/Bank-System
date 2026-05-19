package com.bank.controller;

import com.bank.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    private final AccountService accountService;

    public ViewController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/accounts-page")
    public String accounts(Model model) {

        model.addAttribute(
                "accounts",
                accountService.getAll()
        );

        return "accounts";
    }

    @GetMapping("/deposit-page")
    public String depositPage() {
        return "deposit";
    }

    @GetMapping("/withdraw-page")
    public String withdrawPage() {
        return "withdraw";
    }

    @GetMapping("/transfer-page")
    public String transferPage() {
        return "transfer";
    }
}