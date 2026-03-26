package com.example.accounting.dto;

import com.example.accounting.entity.Account;

public class AccountMapper {

    public static AccountResponseDto toDto(Account account) {
        AccountResponseDto dto = new AccountResponseDto();
        dto.setId(account.getId());
        dto.setName(account.getName());
        dto.setCode(account.getCode());
        dto.setType(account.getType());
        return dto;
    }
}

