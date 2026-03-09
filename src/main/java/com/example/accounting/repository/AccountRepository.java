package com.example.accounting.repository;

import com.example.accounting.entity.Account;
import com.example.accounting.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByUser(User user);

    Optional<Account> findByIdAndUser(Long id, User user);

    boolean existsByCodeAndUser(String code, User user);
}

