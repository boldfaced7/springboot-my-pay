package com.mypay.firmbanking.adapter.out.persistence;

import com.mypay.common.PersistenceAdapter;
import com.mypay.firmbanking.application.port.out.GetFirmbankingRequestPort;
import com.mypay.firmbanking.application.port.out.ListFirmbankingRequestsPort;
import com.mypay.firmbanking.application.port.out.RegisterRequestFirmbankingPort;
import com.mypay.firmbanking.application.port.out.UpdateRequestFirmbankingPort;
import com.mypay.firmbanking.domain.FirmbankingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class FirmbankingRequestPersistenceAdapter implements
        RegisterRequestFirmbankingPort,
        UpdateRequestFirmbankingPort,
        GetFirmbankingRequestPort,
        ListFirmbankingRequestsPort {

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

    @Override
    public Optional<FirmbankingRequest> findById(FirmbankingRequest.Id id) {
        return firmbankingRequestJpaRepository.findById(Long.parseLong(id.value()))
                .map(FirmbankingRequestMapper::mapToDomain);
    }

    @Override
    public List<FirmbankingRequest> listByMembershipId(
            FirmbankingRequest.MembershipId membershipId,
            int pageNumber,
            int pageSize
    ) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return firmbankingRequestJpaRepository
                .findByMembershipId(membershipId.value(), pageable)
                .stream()
                .map(FirmbankingRequestMapper::mapToDomain)
                .toList();

    }
}
