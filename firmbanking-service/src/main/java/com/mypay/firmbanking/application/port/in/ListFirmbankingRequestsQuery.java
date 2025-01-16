package com.mypay.firmbanking.application.port.in;

import com.mypay.firmbanking.domain.FirmbankingRequest;

import java.util.List;

public interface ListFirmbankingRequestsQuery {
    List<FirmbankingRequest> listFirmbankingRequests(ListFirmbankingRequestsCommand command);
}
