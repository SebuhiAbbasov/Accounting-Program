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

    @PostMapping
    public Transaction create(
            @RequestParam Long debitAccountId,
            @RequestParam Long creditAccountId,
            @RequestParam BigDecimal amount,
            @RequestParam String description,
            @RequestParam LocalDate date
    ) {
        return transactionService.createTransaction(
                debitAccountId, creditAccountId, amount, description, date
        );
    }

    @GetMapping("/ledger")
    public List<Transaction> ledger(
            @RequestParam LocalDate from,
            @RequestParam LocalDate to
    ) {
        return transactionService.getLedger(from, to);
    }
}

