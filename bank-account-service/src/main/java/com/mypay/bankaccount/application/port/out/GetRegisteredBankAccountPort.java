package com.mypay.bankaccount.application.port.out;

import com.mypay.bankaccount.domain.RegisteredBankAccount;

import java.util.Optional;

public interface GetRegisteredBankAccountPort {
    Optional<RegisteredBankAccount> findRegisteredBankAccountByMembershipId(
            RegisteredBankAccount.MembershipId membershipId
    );
}
