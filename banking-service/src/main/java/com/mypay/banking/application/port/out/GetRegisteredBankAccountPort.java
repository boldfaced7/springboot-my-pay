package com.mypay.banking.application.port.out;

import com.mypay.banking.domain.RegisteredBankAccount;

import java.util.Optional;

public interface GetRegisteredBankAccountPort {
    Optional<RegisteredBankAccount> findRegisteredBankAccountByMembershipId(
            RegisteredBankAccount.MembershipId membershipId
    );
}
