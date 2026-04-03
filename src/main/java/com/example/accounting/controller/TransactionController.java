package com.example.accounting.controller;

import com.example.accounting.dto.TransactionMapper;
import com.example.accounting.dto.TransactionRequestDto;
import com.example.accounting.dto.TransactionResponseDto;
import com.example.accounting.entity.Transaction;
import com.example.accounting.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    // CREATE TRANSACTION
    @PostMapping
    public TransactionResponseDto create(
            @Valid @RequestBody TransactionRequestDto dto
    ) {
        return TransactionMapper.toDto(
                transactionService.createTransaction(
                        dto.getDebitAccountId(),
                        dto.getCreditAccountId(),
                        dto.getAmount(),
                        dto.getDescription(),
                        dto.getDate()
                )
        );
    }

    // UPDATE TRANSACTION
    @PutMapping("/{id}")
    public TransactionResponseDto update(
            @PathVariable Long id,
            @Valid @RequestBody TransactionRequestDto dto
    ) {
        return TransactionMapper.toDto(
                transactionService.updateTransaction(
                        id,
                        dto.getDebitAccountId(),
                        dto.getCreditAccountId(),
                        dto.getAmount(),
                        dto.getDescription(),
                        dto.getDate()
                )
        );
    }

    // DELETE TRANSACTION
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
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


