package com.mypay.banking.application.port.out;

public record BankAccountInfoRequest(
        String bankName,
        String bankAccountNumber
) {
}
