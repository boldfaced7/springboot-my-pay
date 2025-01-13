package com.mypay.firmbanking.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FirmbankingRequestJpaRepository
    extends JpaRepository<FirmbankingRequestJpaEntity, Long> {}
