package com.acmebank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZonedDateTime;

@SpringBootApplication
public class BankAccountManagerApplication {

    public static void main(String[] args) {
        System.out.println(ZonedDateTime.now());
        SpringApplication.run(BankAccountManagerApplication.class, args);
    }

}
