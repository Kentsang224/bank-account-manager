package com.acmebank.accountmanager.request;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class TransferMoneyRequest {
    @NotNull
    public String transferorAccountNumber;

    @NotNull
    public String transfereeAccountNumber;

    @NotNull
    public BigDecimal amount;
}
