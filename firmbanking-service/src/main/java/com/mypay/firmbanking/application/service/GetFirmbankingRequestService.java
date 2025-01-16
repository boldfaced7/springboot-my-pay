package com.mypay.firmbanking.application.service;

import com.mypay.common.Query;
import com.mypay.firmbanking.application.port.in.GetFirmbankingRequestCommand;
import com.mypay.firmbanking.application.port.in.GetFirmbankingRequestQuery;
import com.mypay.firmbanking.application.port.out.GetFirmbankingRequestPort;
import com.mypay.firmbanking.domain.FirmbankingRequest;
import lombok.RequiredArgsConstructor;

@Query
@RequiredArgsConstructor
public class GetFirmbankingRequestService implements GetFirmbankingRequestQuery {

    private final GetFirmbankingRequestPort getFirmbankingRequestPort;

    @Override
    public FirmbankingRequest getFirmbankingRequest(GetFirmbankingRequestCommand command) {
        FirmbankingRequest.Id id
                = new FirmbankingRequest.Id(command.getFirmbankingRequestId());
        return getFirmbankingRequestPort.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }
}
