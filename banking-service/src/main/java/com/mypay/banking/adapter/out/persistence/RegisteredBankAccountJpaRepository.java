package com.mypay.banking.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisteredBankAccountJpaRepository
    extends JpaRepository<RegisteredBankAccountJpaEntity, Long> {
    Optional<RegisteredBankAccountJpaEntity> findByMembershipId(String membershipId);
}
