package com.mypay.firmbanking.application.port.out;

import com.mypay.firmbanking.domain.FirmbankingRequest;

public interface RequestFirmbankingPort {
    FirmbankingRequest saveFirmbankingRequest(FirmbankingRequest request);
    FirmbankingRequest updateFirmbankingRequest(FirmbankingRequest request);
}
