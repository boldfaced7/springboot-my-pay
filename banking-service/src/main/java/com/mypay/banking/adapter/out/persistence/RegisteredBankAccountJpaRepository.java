package com.mypay.banking.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisteredBankAccountJpaRepository
    extends JpaRepository<RegisteredBankAccountJpaEntity, Long> {}
