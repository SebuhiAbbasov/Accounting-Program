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

    @PostMapping
    public Account create(
            @RequestParam String name,
            @RequestParam String code,
            @RequestParam AccountType type
    ) {
        return accountService.createAccount(name, code, type);
    }

    @GetMapping
    public List<Account> getAll() {
        return accountService.getAccounts();
    }
}

