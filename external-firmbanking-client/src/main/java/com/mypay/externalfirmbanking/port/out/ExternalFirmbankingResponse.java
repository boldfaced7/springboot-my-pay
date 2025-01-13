package com.mypay.externalfirmbanking.port.out;

import com.mypay.common.FirmbankingStatus;

public record ExternalFirmbankingResponse(
        FirmbankingStatus status
) {}
