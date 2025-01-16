package com.mypay.firmbanking.application.service;

import com.mypay.common.Query;
import com.mypay.firmbanking.application.port.in.ListFirmbankingRequestsCommand;
import com.mypay.firmbanking.application.port.in.ListFirmbankingRequestsQuery;
import com.mypay.firmbanking.application.port.out.ListFirmbankingRequestsPort;
import com.mypay.firmbanking.domain.FirmbankingRequest;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Query
@RequiredArgsConstructor
public class ListFirmbankingRequestsService implements ListFirmbankingRequestsQuery {

    private static final int PAGE_SIZE = 10;


    private final ListFirmbankingRequestsPort listFirmbankingRequestsPort;

    @Override
    public List<FirmbankingRequest> listFirmbankingRequests(ListFirmbankingRequestsCommand command) {
        FirmbankingRequest.MembershipId membershipId
                = new FirmbankingRequest.MembershipId(command.getMembershipId());

        return listFirmbankingRequestsPort
                .listByMembershipId(membershipId, command.getPageNumber(), PAGE_SIZE);
    }
}
