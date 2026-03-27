package com.example.accounting.dto;

import com.example.accounting.enums.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRequestDto {

    @NotBlank(message = "Account name cannot be empty")
    private String name;

    @NotBlank(message = "Account code cannot be empty")
    private String code;

    @NotNull(message = "Account type is required")
    private AccountType type;
}


