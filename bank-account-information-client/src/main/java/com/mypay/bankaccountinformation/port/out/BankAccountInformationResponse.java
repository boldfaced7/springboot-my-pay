package com.mypay.bankaccountinformation.port.out;

public record BankAccountInformationResponse(
        String bankName,
        String bankAccountNumber,
        boolean valid
) {}
