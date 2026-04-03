package com.example.accounting;

import com.example.accounting.entity.Account;
import com.example.accounting.entity.User;
import com.example.accounting.enums.AccountType;
import com.example.accounting.repository.AccountRepository;
import com.example.accounting.service.AccountService;
import com.example.accounting.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private AccountService accountService;

    @Test
    void createAccount_WhenSuccess_ShouldReturnCreatedAccount() {
        // Arrange
        String name = "Cash";
        String code = "1000";
        AccountType type = AccountType.ACTIVE;

        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");

        Account savedAccount = new Account();
        savedAccount.setId(1L);
        savedAccount.setName(name);
        savedAccount.setCode(code);
        savedAccount.setType(type);
        savedAccount.setUser(user);

        when(userService.getCurrentUser()).thenReturn(user);
        when(accountRepository.existsByCodeAndUser(code, user)).thenReturn(false);
        when(accountRepository.save(any(Account.class))).thenReturn(savedAccount);

        // Act
        Account result = accountService.createAccount(name, code, type);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(name, result.getName());
        assertEquals(code, result.getCode());
        assertEquals(type, result.getType());
        assertEquals(user, result.getUser());

        verify(userService).getCurrentUser();
        verify(accountRepository).existsByCodeAndUser(code, user);
        verify(accountRepository).save(any(Account.class));
    }
}
