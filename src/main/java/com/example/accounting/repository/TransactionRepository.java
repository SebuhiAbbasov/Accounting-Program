package com.example.accounting.repository;

import com.example.accounting.entity.Account;
import com.example.accounting.entity.Transaction;
import com.example.accounting.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByUserOrderByTransactionDateAsc(User user);

    List<Transaction> findAllByUserAndTransactionDateBetween(
            User user,
            LocalDate from,
            LocalDate to
    );

    List<Transaction> findAllByDebitAccountOrCreditAccount(
            Account debit,
            Account credit
    );

    List<Transaction> findAllByDebitAccountOrCreditAccountAndTransactionDateBetween(
            Account debit,
            Account credit,
            LocalDate from,
            LocalDate to
    );
}

