package com.mypay.banking.adapter.out.external.bank;

import com.mypay.banking.application.port.out.BankAccountInfoRequest;
import com.mypay.banking.application.port.out.BankAccountInfoResponse;
import com.mypay.banking.application.port.out.RequestBankAccountInfoPort;
import com.mypay.common.ExternalSystemAdapter;
import lombok.RequiredArgsConstructor;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class NoOpBankAccountInfoAdapter implements RequestBankAccountInfoPort {

    @Override
    public BankAccountInfoResponse getBankAccountInfo(BankAccountInfoRequest request) {
        return new BankAccountInfoResponse(
                request.bankName(),
                request.bankAccountNumber(),
                true
        );
    }
}
