package com.mypay.firmbanking.application.port.in;

import com.mypay.firmbanking.domain.FirmbankingRequest;

public interface RequestFirmbankingUseCase {
    FirmbankingRequest requestFirmbanking(RequestFirmbankingCommand command);
}
