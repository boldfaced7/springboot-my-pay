package com.mypay.banking.application.service;

import com.mypay.banking.application.port.in.GetRegisteredBankAccountCommand;
import com.mypay.banking.application.port.in.GetRegisteredBankAccountQuery;
import com.mypay.banking.application.port.out.*;
import com.mypay.banking.domain.RegisteredBankAccount;
import com.mypay.common.Query;
import lombok.RequiredArgsConstructor;

@Query
@RequiredArgsConstructor
public class GetRegisterdBankAccountService implements GetRegisteredBankAccountQuery {

    private final GetRegisteredBankAccountPort getRegisteredBankAccountPort;
    private final RequestBankAccountInfoPort requestBankAccountInfoPort;

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
        BankAccountInfoRequest request = new BankAccountInfoRequest(
                bankAccount.getBankName(),
                bankAccount.getBankAccountNumber()
        );
        BankAccountInfoResponse response
                = requestBankAccountInfoPort.getBankAccountInfo(request);

        return response.valid();
    }
}
