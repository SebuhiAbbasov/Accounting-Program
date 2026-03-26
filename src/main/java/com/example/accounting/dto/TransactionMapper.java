package com.example.accounting.dto;

import com.example.accounting.entity.Transaction;

public class TransactionMapper {

    public static TransactionResponseDto toDto(Transaction tx) {
        TransactionResponseDto dto = new TransactionResponseDto();
        dto.setId(tx.getId());
        dto.setDebitAccount(tx.getDebitAccount().getName());
        dto.setCreditAccount(tx.getCreditAccount().getName());
        dto.setAmount(tx.getAmount());
        dto.setDescription(tx.getDescription());
        dto.setDate(tx.getTransactionDate());
        return dto;
    }
}

