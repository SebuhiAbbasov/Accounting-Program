package com.example.accounting.service;

import com.example.accounting.entity.Account;
import com.example.accounting.entity.Transaction;
import com.example.accounting.entity.User;
import com.example.accounting.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    private final UserService userService;

    public Transaction createTransaction(
            Long debitAccountId,
            Long creditAccountId,
            BigDecimal amount,
            String description,
            LocalDate date
    ) {

        if (debitAccountId.equals(creditAccountId)) {
            throw new RuntimeException("Debit and Credit accounts cannot be same");
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Amount must be greater than zero");
        }

        Account debit = accountService.getAccountById(debitAccountId);
        Account credit = accountService.getAccountById(creditAccountId);
        User user = userService.getCurrentUser();

        Transaction tx = new Transaction();
        tx.setDebitAccount(debit);
        tx.setCreditAccount(credit);
        tx.setAmount(amount);
        tx.setDescription(description);
        tx.setTransactionDate(date);
        tx.setUser(user);

        return transactionRepository.save(tx);
    }

    public List<Transaction> getLedger(LocalDate from, LocalDate to) {
        return transactionRepository
                .findAllByUserAndTransactionDateBetween(
                        userService.getCurrentUser(), from, to);
    }

    public List<Transaction> getAccountStatement(
            Long accountId,
            LocalDate from,
            LocalDate to
    ) {
        Account account = accountService.getAccountById(accountId);

        return transactionRepository
                .findAllByDebitAccountOrCreditAccountAndTransactionDateBetween(
                        account, account, from, to
                );
    }
}

