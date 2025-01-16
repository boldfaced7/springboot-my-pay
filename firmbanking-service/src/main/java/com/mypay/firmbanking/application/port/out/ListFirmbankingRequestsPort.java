package com.mypay.firmbanking.application.port.out;

import com.mypay.firmbanking.domain.FirmbankingRequest;

import java.util.List;

public interface ListFirmbankingRequestsPort {
    List<FirmbankingRequest> listByMembershipId(
            FirmbankingRequest.MembershipId membershipId,
            int pageNumber,
            int pageSize
    );
}
