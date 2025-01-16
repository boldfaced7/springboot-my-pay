package com.mypay.bankaccount.application.service;

import com.mypay.bankaccount.application.port.in.GetRegisteredBankAccountCommand;
import com.mypay.bankaccount.application.port.in.GetRegisteredBankAccountQuery;
import com.mypay.bankaccount.application.port.out.GetRegisteredBankAccountPort;
import com.mypay.bankaccount.domain.RegisteredBankAccount;
import com.mypay.common.Query;
import lombok.RequiredArgsConstructor;

@Query
@RequiredArgsConstructor
public class GetRegisteredBankAccountService implements GetRegisteredBankAccountQuery {

    private final GetRegisteredBankAccountPort getRegisteredBankAccountPort;

    @Override
    public RegisteredBankAccount getRegisteredBankAccount(GetRegisteredBankAccountCommand command) {
        RegisteredBankAccount.MembershipId membershipId
                = new RegisteredBankAccount.MembershipId(command.getMembershipId());

        return getRegisteredBankAccountPort
                .findByMembershipId(membershipId)
                .orElseThrow(IllegalArgumentException::new);
    }
}
