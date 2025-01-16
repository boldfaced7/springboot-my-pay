package com.mypay.firmbanking.application.port.out;

import com.mypay.firmbanking.domain.FirmbankingRequest;

public interface UpdateRequestFirmbankingPort {
    FirmbankingRequest updateFirmbankingRequest(FirmbankingRequest request);
}
