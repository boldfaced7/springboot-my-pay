package com.mypay.banking.adapter.out.persistence;

import com.mypay.banking.domain.RegisteredBankAccount;

public class RegisteredBankAccountMapper {

    public static RegisteredBankAccount mapToDomain(
            RegisteredBankAccountJpaEntity registeredBankAccount
    ) {
        return RegisteredBankAccount.generate(
                new RegisteredBankAccount.Id(
                        registeredBankAccount.getId()),
                new RegisteredBankAccount.MembershipId(
                        registeredBankAccount.getMembershipId()),
                new RegisteredBankAccount.BankName(
                        registeredBankAccount.getBankName()),
                new RegisteredBankAccount.BankAccountNumber(
                        registeredBankAccount.getBankAccountNumber()),
                new RegisteredBankAccount.ValidLinkedStatus(
                        registeredBankAccount.isValidLinkedStatus())
        );
    }

    public static RegisteredBankAccountJpaEntity mapToJpaEntity(
            RegisteredBankAccount registeredBankAccount
    ) {
        return new RegisteredBankAccountJpaEntity(
                registeredBankAccount.getId(),
                registeredBankAccount.getMembershipId(),
                registeredBankAccount.getBankName(),
                registeredBankAccount.getBankAccountNumber(),
                registeredBankAccount.isValidLinkedStatus()
        );
    }
}
