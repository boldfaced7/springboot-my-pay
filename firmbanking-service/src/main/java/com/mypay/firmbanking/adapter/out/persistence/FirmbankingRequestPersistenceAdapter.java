package com.mypay.firmbanking.adapter.out.persistence;

import com.mypay.common.PersistenceAdapter;
import com.mypay.firmbanking.application.port.out.RequestFirmbankingPort;
import com.mypay.firmbanking.domain.FirmbankingRequest;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class FirmbankingRequestPersistenceAdapter
        implements RequestFirmbankingPort {

    private final FirmbankingRequestJpaRepository firmbankingRequestJpaRepository;

    @Override
    public FirmbankingRequest saveFirmbankingRequest(FirmbankingRequest request) {
        FirmbankingRequestJpaEntity source = FirmbankingRequestMapper.mapToJpaEntity(request);
        FirmbankingRequestJpaEntity saved = firmbankingRequestJpaRepository.save(source);
        return FirmbankingRequestMapper.mapToDomain(saved);
    }

    @Override
    public FirmbankingRequest updateFirmbankingRequest(FirmbankingRequest request) {
        FirmbankingRequestJpaEntity source = FirmbankingRequestMapper.mapToJpaEntity(request);
        FirmbankingRequestJpaEntity updated = firmbankingRequestJpaRepository.save(source);
        return FirmbankingRequestMapper.mapToDomain(updated);
    }
}
