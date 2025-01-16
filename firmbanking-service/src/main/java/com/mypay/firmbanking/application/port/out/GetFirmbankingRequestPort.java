package com.mypay.firmbanking.application.port.out;

import com.mypay.firmbanking.domain.FirmbankingRequest;

import java.util.Optional;

public interface GetFirmbankingRequestPort {
    Optional<FirmbankingRequest> findById(FirmbankingRequest.Id id);
}
