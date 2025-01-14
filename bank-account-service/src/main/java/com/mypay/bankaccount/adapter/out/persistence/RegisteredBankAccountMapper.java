package com.mypay.bankaccount.adapter.out.persistence;

import com.mypay.bankaccount.domain.RegisteredBankAccount;

public class RegisteredBankAccountMapper {

    public static RegisteredBankAccount mapToDomain(
            RegisteredBankAccountJpaEntity jpaEntity
    ) {
        return RegisteredBankAccount.generate(
                new RegisteredBankAccount.Id(
                        jpaEntity.getId()+""),
                new RegisteredBankAccount.MembershipId(
                        jpaEntity.getMembershipId()),
                new RegisteredBankAccount.BankName(
                        jpaEntity.getBankName()),
                new RegisteredBankAccount.BankAccountNumber(
                        jpaEntity.getBankAccountNumber()),
                new RegisteredBankAccount.ValidLinkedStatus(
                        jpaEntity.isValidLinkedStatus()),
                jpaEntity.getCreatedAt(),
                jpaEntity.getUpdatedAt(),
                jpaEntity.getDeletedAt()
        );
    }

    public static RegisteredBankAccountJpaEntity mapToJpaEntity(
            RegisteredBankAccount domainEntity
    ) {
        return new RegisteredBankAccountJpaEntity(
                parseId(domainEntity.getId()),
                domainEntity.getMembershipId(),
                domainEntity.getBankName(),
                domainEntity.getBankAccountNumber(),
                domainEntity.isValidLinkedStatus(),
                domainEntity.getCreatedAt(),
                domainEntity.getUpdatedAt(),
                domainEntity.getDeletedAt()
        );
    }

    private static Long parseId(String id) {
        try {
            return Long.parseLong(id);
        } catch (Exception e) {
            return null;
        }
    }
}
