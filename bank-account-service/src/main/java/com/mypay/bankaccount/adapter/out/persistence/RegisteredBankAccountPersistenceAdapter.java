package com.mypay.bankaccount.adapter.out.persistence;

import com.mypay.bankaccount.application.port.out.GetRegisteredBankAccountPort;
import com.mypay.bankaccount.application.port.out.RegisterBankAccountPort;
import com.mypay.bankaccount.domain.RegisteredBankAccount;
import com.mypay.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class RegisteredBankAccountPersistenceAdapter
        implements RegisterBankAccountPort, GetRegisteredBankAccountPort {

    private final RegisteredBankAccountJpaRepository bankAccountRepository;

    @Override
    public RegisteredBankAccount save(
            RegisteredBankAccount registeredBankAccount
    ) {
        return registeredBankAccount
                .map(RegisteredBankAccountMapper::mapToJpaEntity)
                .map(bankAccountRepository::save)
                .map(RegisteredBankAccountMapper::mapToDomain);
    }

    @Override
    public Optional<RegisteredBankAccount> findByMembershipId(
            RegisteredBankAccount.MembershipId membershipId
    ) {
        return bankAccountRepository.findByMembershipId(membershipId.value())
                .map(RegisteredBankAccountMapper::mapToDomain);
    }
}
