package com.acmebank.accountmanager.service;

import com.acmebank.accountmanager.domain.BankAccount;
import com.acmebank.accountmanager.error.BankOperationErrorCode;
import com.acmebank.accountmanager.repository.BankAccountRepository;
import com.acmebank.accountmanager.request.TransferMoneyRequest;
import com.acmebank.accountmanager.response.GetBalanceResponse;
import com.acmebank.accountmanager.response.TransferMoneyResponse;
import org.springframework.stereotype.Service;

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

    public TransferMoneyResponse transfer(TransferMoneyRequest request) {
        Optional<BankAccount> transferorAccountOptional = bankAccountRepository.findById(request.transferorAccountNumber);
        if (transferorAccountOptional.isEmpty())
            return TransferMoneyResponse.failedOf(BankOperationErrorCode.BANK_ACCOUNT_NOT_EXIST);

        BankAccount transferorAccount = transferorAccountOptional.get();

        if (request.amount.compareTo(transferorAccount.balance) > 0)
            return TransferMoneyResponse.failedOf(BankOperationErrorCode.INSUFFICIENT_BALANCE);

        Optional<BankAccount> transfereeAccountOptional = bankAccountRepository.findById(request.transfereeAccountNumber);
        if (transfereeAccountOptional.isEmpty())
            return TransferMoneyResponse.failedOf(BankOperationErrorCode.BANK_ACCOUNT_NOT_EXIST);

        BankAccount transfereeAccount = transfereeAccountOptional.get();

        transferorAccount.balance = transferorAccount.balance.subtract(request.amount);
        transfereeAccount.balance = transfereeAccount.balance.add(request.amount);

        bankAccountRepository.save(transferorAccount);
        bankAccountRepository.save(transfereeAccount);

        return TransferMoneyResponse.success();
    }
}
