package com.example.accounting.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class TransactionRequestDto {

    @NotNull
    private Long debitAccountId;

    @NotNull
    private Long creditAccountId;

    @NotNull
    @Positive(message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotBlank
    private String description;

    @NotNull
    private LocalDate date;
}


