package com.acmebank.accountmanager.response;

import com.acmebank.accountmanager.error.BankOperationErrorCode;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class GetBalanceResponse {
    @NotNull
    public Boolean success;

    public BigDecimal balance;

    public BankOperationErrorCode errorCode;

    public static GetBalanceResponse successWith(BigDecimal balance) {
        var response = new GetBalanceResponse();
        response.success = Boolean.TRUE;
        response.balance = balance;
        return response;
    }

    public static GetBalanceResponse failedOf(BankOperationErrorCode errorCode) {
        var response = new GetBalanceResponse();
        response.success = Boolean.FALSE;
        response.errorCode = errorCode;
        return response;
    }
}
