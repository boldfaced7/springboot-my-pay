package com.mypay.externalfirmbanking.adapter.out.external.bank;

import com.mypay.common.ExternalSystemAdapter;
import com.mypay.common.FirmbankingStatus;
import com.mypay.externalfirmbanking.port.out.ExternalFirmbankingRequest;
import com.mypay.externalfirmbanking.port.out.ExternalFirmbankingResponse;
import com.mypay.externalfirmbanking.port.out.RequestExternalFirmbankingPort;
import lombok.RequiredArgsConstructor;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class NoOpExternalFirmbankingRequestAdapter implements RequestExternalFirmbankingPort {

    @Override
    public ExternalFirmbankingResponse requestExternalFirmbanking(ExternalFirmbankingRequest request) {
        return new ExternalFirmbankingResponse(FirmbankingStatus.SUCCESS);
    }
}
