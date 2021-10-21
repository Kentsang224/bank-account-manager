package com.acmebank.accountmanager.service;

import com.acmebank.accountmanager.domain.BankAccount;
import com.acmebank.accountmanager.error.BankOperationErrorCode;
import com.acmebank.accountmanager.repository.BankAccountRepository;
import com.acmebank.accountmanager.response.GetBalanceResponse;
import com.acmebank.accountmanager.response.TransferMoneyResponse;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;

@Service
public class AccountService {

    private final BankAccountRepository bankAccountRepository;

    public AccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public GetBalanceResponse getBalance(String accountNumber) {
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(accountNumber);
        if (bankAccount.isEmpty()) return GetBalanceResponse.failedOf(BankOperationErrorCode.BANK_ACCOUNT_NOT_EXIST);
        return GetBalanceResponse.successWith(bankAccount.get().balance);
    }

    @Transactional
    public TransferMoneyResponse transfer(String transferorAccountNumber, String transfereeAccountNumber, BigDecimal amount) {
        Optional<BankAccount> transferorAccountOptional = bankAccountRepository.findById(transferorAccountNumber);
        if (transferorAccountOptional.isEmpty())
            return TransferMoneyResponse.failedOf(BankOperationErrorCode.BANK_ACCOUNT_NOT_EXIST);

        BankAccount transferorAccount = transferorAccountOptional.get();
        if (amount.compareTo(transferorAccount.balance) > 0) {
            return TransferMoneyResponse.failedOf(BankOperationErrorCode.INSUFFICIENT_BALANCE);
        }

        Optional<BankAccount> transfereeAccountOptional = bankAccountRepository.findById(transfereeAccountNumber);
        if (transfereeAccountOptional.isEmpty())
            return TransferMoneyResponse.failedOf(BankOperationErrorCode.BANK_ACCOUNT_NOT_EXIST);

        BankAccount transfereeAccount = transfereeAccountOptional.get();

        transferorAccount.balance = transferorAccount.balance.subtract(amount);
        transferorAccount.updatedTime = ZonedDateTime.now();

        transfereeAccount.balance = transfereeAccount.balance.add(amount);
        transfereeAccount.updatedTime = ZonedDateTime.now();

        bankAccountRepository.save(transferorAccount);
        bankAccountRepository.save(transfereeAccount);

        return TransferMoneyResponse.success();
    }
}
