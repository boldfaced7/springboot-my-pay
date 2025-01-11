package com.mypay.banking.application.port.out;

public interface RequestBankAccountInfoPort {
    BankAccountInfoResponse getBankAccountInfo(BankAccountInfoRequest request);
}
