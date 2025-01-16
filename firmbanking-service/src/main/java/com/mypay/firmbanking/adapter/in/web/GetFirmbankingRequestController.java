package com.mypay.firmbanking.adapter.in.web;

import com.mypay.common.FirmbankingStatus;
import com.mypay.common.WebAdapter;
import com.mypay.firmbanking.application.port.in.GetFirmbankingRequestCommand;
import com.mypay.firmbanking.application.port.in.GetFirmbankingRequestQuery;
import com.mypay.firmbanking.domain.FirmbankingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class GetFirmbankingRequestController {

    private final GetFirmbankingRequestQuery getFirmbankingRequestQuery;

    @GetMapping(path = "/banking/firmbanking/request/{requestId}")
    ResponseEntity<GetFirmbankingRequestResponse> getFirmbankingRequest(
            @PathVariable String requestId
    ) {
        GetFirmbankingRequestCommand command = new GetFirmbankingRequestCommand(requestId);
        FirmbankingRequest found = getFirmbankingRequestQuery.getFirmbankingRequest(command);
        GetFirmbankingRequestResponse response = mapToResponse(found);

        return ResponseEntity.ok(response);
    }
    private GetFirmbankingRequestResponse mapToResponse(FirmbankingRequest request) {
        return new GetFirmbankingRequestResponse(
                request.getId(),
                request.getMembershipId(),
                request.getFromBankName(),
                request.getFromBankAccountNumber(),
                request.getToBankName(),
                request.getToBankAccountNumber(),
                request.getMoneyAmount(),
                request.getFirmbankingStatus(),
                request.getUuid()
        );
    }

    public record GetFirmbankingRequestResponse(
            String id,
            String membershipId,
            String fromBankName,
            String fromBankAccountNumber,
            String toBankName,
            String toBankAccountNumber,
            int moneyAmount,
            FirmbankingStatus firmbankingStatus,
            UUID uuid
    ) {}
}
