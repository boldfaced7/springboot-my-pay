package com.mypay.bankaccountinformation.adapter.out.external.bank;

import com.mypay.bankaccountinformation.port.out.BankAccountInformationRequest;
import com.mypay.bankaccountinformation.port.out.BankAccountInformationResponse;
import com.mypay.bankaccountinformation.port.out.RequestBankAccountInformationPort;
import com.mypay.common.ExternalSystemAdapter;
import lombok.RequiredArgsConstructor;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class NoOpBankAccountInformationAdapter implements RequestBankAccountInformationPort {

    @Override
    public BankAccountInformationResponse getBankAccountInfo(BankAccountInformationRequest request) {
        return new BankAccountInformationResponse(
                request.bankName(),
                request.bankAccountNumber(),
                true
        );
    }
}
