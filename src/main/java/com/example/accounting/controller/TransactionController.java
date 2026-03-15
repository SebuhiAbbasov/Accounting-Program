package com.example.accounting.controller;

import com.example.accounting.entity.Transaction;
import com.example.accounting.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    // CREATE TRANSACTION
    @PostMapping
    public Transaction createTransaction(
            @RequestParam Long debitAccountId,
            @RequestParam Long creditAccountId,
            @RequestParam BigDecimal amount,
            @RequestParam(required = false) String description,
            @RequestParam LocalDate date
    ) {
        return transactionService.createTransaction(
                debitAccountId, creditAccountId, amount, description, date
        );
    }

    // ACCOUNT STATEMENT
    @GetMapping("/account/{accountId}")
    public List<Transaction> accountStatement(
            @PathVariable Long accountId,
            @RequestParam LocalDate from,
            @RequestParam LocalDate to
    ) {
        return transactionService.getAccountStatement(accountId, from, to);
    }
}


