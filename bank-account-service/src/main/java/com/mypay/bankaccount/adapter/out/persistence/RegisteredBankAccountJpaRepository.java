package com.mypay.bankaccount.adapter.out.persistence;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface RegisteredBankAccountJpaRepository
    extends Repository<RegisteredBankAccountJpaEntity, Long> {
    RegisteredBankAccountJpaEntity save(RegisteredBankAccountJpaEntity registeredBankAccount);
    Optional<RegisteredBankAccountJpaEntity> findByMembershipId(String membershipId);
}
