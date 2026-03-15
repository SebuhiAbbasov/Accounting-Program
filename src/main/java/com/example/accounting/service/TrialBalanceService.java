package com.example.accounting.service;

import com.example.accounting.entity.Account;
import com.example.accounting.entity.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TrialBalanceService {

    private final AccountService accountService;
    private final TransactionService transactionService;

    public Map<String, Object> calculate(LocalDate from, LocalDate to) {

        List<Account> accounts = accountService.getAccounts();
        List<Transaction> transactions = transactionService.getLedger(from, to);

        List<Map<String, Object>> result = new ArrayList<>();

        for (Account account : accounts) {

            BigDecimal debitSum = BigDecimal.ZERO;
            BigDecimal creditSum = BigDecimal.ZERO;

            for (Transaction tx : transactions) {

                if (tx.getDebitAccount().getId().equals(account.getId())) {
                    debitSum = debitSum.add(tx.getAmount());
                }

                if (tx.getCreditAccount().getId().equals(account.getId())) {
                    creditSum = creditSum.add(tx.getAmount());
                }
            }

            BigDecimal balance = debitSum.subtract(creditSum);

            Map<String, Object> row = new HashMap<>();
            row.put("accountName", account.getName());
            row.put("debitTotal", debitSum);
            row.put("creditTotal", creditSum);
            row.put("balance", balance);

            result.add(row);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("trialBalance", result);

        return response;
    }
}

