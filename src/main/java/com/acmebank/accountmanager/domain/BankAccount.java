package com.acmebank.accountmanager.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "bank_account")
public class BankAccount {
    @Id
    @Column(name = "accountNumber")
    public String accountNumber;

    @Column(name = "currency", nullable = false)
    @Enumerated(EnumType.STRING)
    public Currency currency;

    @Column(name = "balance", nullable = false)
    public BigDecimal balance;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    public BankStatus status;

    @Column(name = "createdTime", nullable = false)
    public ZonedDateTime createdTime;

    @Column(name = "updatedTime", nullable = false)
    public ZonedDateTime updatedTime;
}
