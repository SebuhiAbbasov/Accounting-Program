package com.example.accounting;

import com.example.accounting.entity.Account;
import com.example.accounting.entity.Transaction;
import com.example.accounting.entity.User;
import com.example.accounting.repository.TransactionRepository;
import com.example.accounting.service.AccountService;
import com.example.accounting.service.TransactionService;
import com.example.accounting.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountService accountService;

    @Mock
    private UserService userService;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    void createTransaction_WhenSuccess_ShouldReturnCreatedTransaction() {
        // Arrange
        Long debitAccountId = 1L;
        Long creditAccountId = 2L;
        BigDecimal amount = BigDecimal.valueOf(100.0);
        String description = "Sample Transaction";
        LocalDate date = LocalDate.now();

        User user = new User();
        user.setId(1L);

        Account debitAccount = new Account();
        debitAccount.setId(debitAccountId);

        Account creditAccount = new Account();
        creditAccount.setId(creditAccountId);

        Transaction savedTransaction = new Transaction();
        savedTransaction.setId(1L);
        savedTransaction.setDebitAccount(debitAccount);
        savedTransaction.setCreditAccount(creditAccount);
        savedTransaction.setAmount(amount);
        savedTransaction.setDescription(description);
        savedTransaction.setTransactionDate(date);
        savedTransaction.setUser(user);

        when(accountService.getAccountById(debitAccountId)).thenReturn(debitAccount);
        when(accountService.getAccountById(creditAccountId)).thenReturn(creditAccount);
        when(userService.getCurrentUser()).thenReturn(user);
        when(transactionRepository.save(any(Transaction.class))).thenReturn(savedTransaction);

        // Act
        Transaction result = transactionService.createTransaction(
                debitAccountId, creditAccountId, amount, description, date
        );

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(amount, result.getAmount());
        assertEquals(description, result.getDescription());
        assertEquals(date, result.getTransactionDate());
        assertEquals(debitAccount, result.getDebitAccount());
        assertEquals(creditAccount, result.getCreditAccount());
        assertEquals(user, result.getUser());

        verify(accountService).getAccountById(debitAccountId);
        verify(accountService).getAccountById(creditAccountId);
        verify(userService).getCurrentUser();
        verify(transactionRepository).save(any(Transaction.class));
    }
}
