package com.example.accounting.controller;

import com.example.accounting.dto.AccountMapper;
import com.example.accounting.dto.AccountRequestDto;
import com.example.accounting.dto.AccountResponseDto;
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
    public AccountResponseDto createAccount(
            @RequestBody AccountRequestDto dto
    ) {
        return AccountMapper.toDto(
                accountService.createAccount(
                        dto.getName(),
                        dto.getCode(),
                        dto.getType()
                )
        );
    }


    // READ ALL
    @GetMapping
    public List<AccountResponseDto> getAllAccounts() {
        return accountService.getAccounts()
                .stream()
                .map(AccountMapper::toDto)
                .toList();
    }


    // READ ONE
    @GetMapping("/{id}")
    public Account getAccount(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }
}


