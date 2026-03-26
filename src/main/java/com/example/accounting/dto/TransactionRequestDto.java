package com.example.accounting.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class TransactionRequestDto {
    private Long debitAccountId;
    private Long creditAccountId;
    private BigDecimal amount;
    private String description;
    private LocalDate date;
}

