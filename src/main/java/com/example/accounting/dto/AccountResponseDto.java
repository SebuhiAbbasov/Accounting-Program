package com.example.accounting.dto;

import com.example.accounting.enums.AccountType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountResponseDto {
    private Long id;
    private String name;
    private String code;
    private AccountType type;
}

