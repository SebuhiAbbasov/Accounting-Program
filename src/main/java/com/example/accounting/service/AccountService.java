package com.example.accounting.service;

import com.example.accounting.entity.Account;
import com.example.accounting.entity.User;
import com.example.accounting.enums.AccountType;
import com.example.accounting.exception.NotFoundException;
import com.example.accounting.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserService userService;

    public Account createAccount(String name, String code, AccountType type) {

        User user = userService.getCurrentUser();

        if (accountRepository.existsByCodeAndUser(code, user)) {
            throw new RuntimeException("Account code already exists");
        }

        Account account = new Account();
        account.setName(name);
        account.setCode(code);
        account.setType(type);
        account.setUser(user);

        return accountRepository.save(account);
    }

    public List<Account> getAccounts() {
        return accountRepository.findAllByUser(userService.getCurrentUser());
    }

    public Account getAccountById(Long id) {
        return accountRepository.findByIdAndUser(id, userService.getCurrentUser())
                .orElseThrow(() -> new NotFoundException("Account not found"));
    }

    public Account updateAccount(Long id, String name, String code, AccountType type) {
        Account account = getAccountById(id);

        // Check if new code already exists in another account
        if (!account.getCode().equals(code) &&
                accountRepository.existsByCodeAndUser(code, userService.getCurrentUser())) {
            throw new RuntimeException("Account code already exists");
        }

        account.setName(name);
        account.setCode(code);
        account.setType(type);

        return accountRepository.save(account);
    }

    public void deleteAccount(Long id) {
        Account account = getAccountById(id);
        // Optional: Check if account has transactions before deleting
        accountRepository.delete(account);
    }
}

