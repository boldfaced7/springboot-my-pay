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
    public RegisteredBankAccount createRegisteredBankAccount(
            RegisteredBankAccount registeredBankAccount
    ) {
        RegisteredBankAccountJpaEntity source
                = RegisteredBankAccountMapper.mapToJpaEntity(registeredBankAccount);
        RegisteredBankAccountJpaEntity saved = bankAccountRepository.save(source);
        return RegisteredBankAccountMapper.mapToDomain(saved);
    }

    @Override
    public Optional<RegisteredBankAccount> findRegisteredBankAccountByMembershipId(
            RegisteredBankAccount.MembershipId membershipId
    ) {
        return bankAccountRepository.findByMembershipId(membershipId.value())
                .map(RegisteredBankAccountMapper::mapToDomain);
    }
}
