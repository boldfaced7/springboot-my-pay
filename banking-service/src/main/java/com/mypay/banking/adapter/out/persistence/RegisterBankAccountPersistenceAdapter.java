package com.mypay.banking.adapter.out.persistence;

import com.mypay.banking.application.port.out.RegisterBankAccountPort;
import com.mypay.banking.domain.RegisteredBankAccount;
import com.mypay.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class RegisterBankAccountPersistenceAdapter
        implements RegisterBankAccountPort {

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
}
