package com.mypay.bankaccountinformation.port.out;

public record BankAccountInformationRequest(
        String bankName,
        String bankAccountNumber
) {
}
