package com.mypay.firmbanking.adapter.out.persistence;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface FirmbankingRequestJpaRepository
    extends Repository<FirmbankingRequestJpaEntity, Long> {
    FirmbankingRequestJpaEntity save(FirmbankingRequestJpaEntity firmbankingRequestJpaEntity);
    Optional<FirmbankingRequestJpaEntity> findById(Long id);
    List<FirmbankingRequestJpaEntity> findByMembershipId(String membershipId, Pageable pageable);
}
