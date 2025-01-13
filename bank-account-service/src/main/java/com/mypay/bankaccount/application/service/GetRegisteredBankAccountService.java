package com.mypay.bankaccount.application.service;

import com.mypay.bankaccount.application.port.in.GetRegisteredBankAccountCommand;
import com.mypay.bankaccount.application.port.in.GetRegisteredBankAccountQuery;
import com.mypay.bankaccountinformation.port.out.BankAccountInformationRequest;
import com.mypay.bankaccountinformation.port.out.BankAccountInformationResponse;
import com.mypay.bankaccount.application.port.out.GetRegisteredBankAccountPort;
import com.mypay.bankaccountinformation.port.out.RequestBankAccountInformationPort;
import com.mypay.bankaccount.domain.RegisteredBankAccount;
import com.mypay.common.Query;
import lombok.RequiredArgsConstructor;

@Query
@RequiredArgsConstructor
public class GetRegisteredBankAccountService implements GetRegisteredBankAccountQuery {

    private final GetRegisteredBankAccountPort getRegisteredBankAccountPort;
    private final RequestBankAccountInformationPort requestBankAccountInformationPort;

    @Override
    public RegisteredBankAccount getRegisteredBankAccount(GetRegisteredBankAccountCommand command) {
        RegisteredBankAccount.MembershipId membershipId
                = new RegisteredBankAccount.MembershipId(command.getMembershipId());

        return getRegisteredBankAccountPort
                .findRegisteredBankAccountByMembershipId(membershipId)
                .filter(this::validateBankAccount)
                .orElseThrow(IllegalStateException::new);
    }

    private boolean validateBankAccount(RegisteredBankAccount bankAccount) {
        BankAccountInformationRequest request = new BankAccountInformationRequest(
                bankAccount.getBankName(),
                bankAccount.getBankAccountNumber()
        );
        BankAccountInformationResponse response
                = requestBankAccountInformationPort.getBankAccountInfo(request);

        return response.valid();
    }
}
