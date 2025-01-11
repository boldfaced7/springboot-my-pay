package com.mypay.banking.application.port.out;

public record BankAccountInfoResponse(
        String bankName,
        String bankAccountNumber,
        boolean valid
) {}
