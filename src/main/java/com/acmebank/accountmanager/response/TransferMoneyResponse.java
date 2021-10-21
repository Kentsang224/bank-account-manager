package com.acmebank.accountmanager.response;

import com.acmebank.accountmanager.error.BankOperationErrorCode;

import javax.validation.constraints.NotNull;

public class TransferMoneyResponse {
    @NotNull
    public Boolean success;

    public BankOperationErrorCode errorCode;

    public static TransferMoneyResponse success() {
        var response = new TransferMoneyResponse();
        response.success = Boolean.TRUE;
        return response;
    }

    public static TransferMoneyResponse failedOf(BankOperationErrorCode errorCode) {
        var response = new TransferMoneyResponse();
        response.success = Boolean.FALSE;
        response.errorCode = errorCode;
        return response;
    }
}
