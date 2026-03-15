package com.example.accounting.controller;

import com.example.accounting.entity.Transaction;
import com.example.accounting.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/ledger")
@RequiredArgsConstructor
public class LedgerController {

    private final TransactionService transactionService;

    @GetMapping
    public List<Transaction> getLedger(
            @RequestParam LocalDate from,
            @RequestParam LocalDate to
    ) {
        return transactionService.getLedger(from, to);
    }
}

