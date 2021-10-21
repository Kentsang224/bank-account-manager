package com.acmebank.accountmanager.service;

import com.acmebank.accountmanager.error.BankOperationErrorCode;
import com.acmebank.accountmanager.response.TransferMoneyResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class AccountServiceTest {
    @Autowired
    AccountService accountService;

    @Test
    void succeedWhenTryToRetrieveBankAccountBalance() {
        assertThat(accountService.getBalance("12345678").balance).isEqualByComparingTo(new BigDecimal("1000000.00"));
    }

    @Test
    void failWhenTryToRetrieveBankAccountBalanceFromNotExistedAccount() {
        assertThat(accountService.getBalance("124133342").success).isFalse();
    }

    @Test
    void succeedWhenTryToTransfer() {
        accountService.transfer("12345678", "88888888", new BigDecimal("10.0"));
        assertThat(accountService.getBalance("12345678").balance).isEqualTo(new BigDecimal("999990.00"));
        assertThat(accountService.getBalance("88888888").balance).isEqualTo(new BigDecimal("1000010.00"));
    }

    @Test
    void failWhenTryToTransferToNotExistedAccount() {
        TransferMoneyResponse response = accountService.transfer("12345678", "8888qwd8qwdwq888", new BigDecimal("20.00"));
        assertThat(response.errorCode).isEqualTo(BankOperationErrorCode.BANK_ACCOUNT_NOT_EXIST);
    }

    @Test
    void failWhenTryToTransferFromNotExistedAccount() {
        TransferMoneyResponse response = accountService.transfer("1234qwdw5678", "88888888", new BigDecimal("10.00"));
        assertThat(response.errorCode).isEqualTo(BankOperationErrorCode.BANK_ACCOUNT_NOT_EXIST);
    }

    @Test
    void failWhenTryToTransferWithInsufficientBalance() {
        TransferMoneyResponse response = accountService.transfer("12345678", "88888888", new BigDecimal("10000000.00"));
        assertThat(response.errorCode).isEqualTo(BankOperationErrorCode.INSUFFICIENT_BALANCE);
    }
}