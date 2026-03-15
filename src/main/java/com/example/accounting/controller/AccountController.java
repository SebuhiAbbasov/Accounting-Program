package com.example.accounting.controller;

import com.example.accounting.entity.Account;
import com.example.accounting.enums.AccountType;
import com.example.accounting.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    // CREATE
    @PostMapping
    public Account createAccount(
            @RequestParam String name,
            @RequestParam String code,
            @RequestParam AccountType type
    ) {
        return accountService.createAccount(name, code, type);
    }

    // READ ALL
    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAccounts();
    }

    // READ ONE
    @GetMapping("/{id}")
    public Account getAccount(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }
}


