package com.example.accounting.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class TransactionResponseDto {
    private Long id;
    private String debitAccount;
    private String creditAccount;
    private BigDecimal amount;
    private String description;
    private LocalDate date;
}

