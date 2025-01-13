package com.mypay.bankaccountinformation.port.out;

public interface RequestBankAccountInformationPort {
    BankAccountInformationResponse getBankAccountInfo(BankAccountInformationRequest request);
}
