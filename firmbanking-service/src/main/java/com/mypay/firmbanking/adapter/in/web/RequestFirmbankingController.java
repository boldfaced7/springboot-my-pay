package com.mypay.firmbanking.adapter.in.web;

import com.mypay.common.FirmbankingStatus;
import com.mypay.common.WebAdapter;
import com.mypay.firmbanking.application.port.in.RequestFirmbankingCommand;
import com.mypay.firmbanking.application.port.in.RequestFirmbankingUseCase;
import com.mypay.firmbanking.domain.FirmbankingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RequestFirmbankingController {

    private final RequestFirmbankingUseCase requestFirmbankingUseCase;

    @PostMapping(path = "/banking/firmbanking/request")
    ResponseEntity<RequestFirmbankingResponse> requestFirmbanking(
            @RequestBody RequestFirmbankingRequest request
    ) {
        RequestFirmbankingCommand command = mapToCommand(request);
        FirmbankingRequest requested = requestFirmbankingUseCase.requestFirmbanking(command);
        RequestFirmbankingResponse response = mapToResponse(requested);
        return ResponseEntity.ok(response);
    }

    private RequestFirmbankingCommand mapToCommand(RequestFirmbankingRequest request) {
        return new RequestFirmbankingCommand(
                request.membershipId(),
                request.fromBankName(),
                request.fromBankAccountNumber(),
                request.toBankName(),
                request.toBankAccountNumber(),
                request.moneyAmount()
        );
    }

    private RequestFirmbankingResponse mapToResponse(FirmbankingRequest request) {
        return new RequestFirmbankingResponse(
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

    public record RequestFirmbankingRequest(
            String membershipId,
            String fromBankName,
            String fromBankAccountNumber,
            String toBankName,
            String toBankAccountNumber,
            int moneyAmount
    ) {}

    public record RequestFirmbankingResponse(
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
