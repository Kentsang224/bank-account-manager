package com.acmebank.accountmanager.controller;

import com.acmebank.accountmanager.request.TransferMoneyRequest;
import com.acmebank.accountmanager.response.GetBalanceResponse;
import com.acmebank.accountmanager.response.TransferMoneyResponse;
import com.acmebank.accountmanager.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/account")
public class AccountServiceController {

    private final AccountService accountService;

    public AccountServiceController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{accountNumber}")
    public GetBalanceResponse getAccountBalance(@PathVariable String accountNumber) {
        return accountService.getBalance(accountNumber);
    }

    @PostMapping(value = "/transfer")
    public TransferMoneyResponse getAccountBalance(@RequestBody TransferMoneyRequest request) {
        return accountService.transfer(request.transferorAccountNumber, request.transfereeAccountNumber, request.amount);
    }

}
