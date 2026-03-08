package com.example.accounting.entity;

import com.example.accounting.constant.AccountType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hesabın_adı", nullable = false)
    private String name;

    @Column(name = "hesabın_nömrəsi", nullable = false)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "hesabın_tipi", nullable = false)
    private AccountType type;

    @CreationTimestamp
    @Column (name = "yaradılma_tarixi")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

