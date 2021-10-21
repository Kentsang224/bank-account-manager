package com.acmebank.accountmanager.repository;

import com.acmebank.accountmanager.domain.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
}
