package com.mypay.firmbanking.adapter.in.web;

import com.mypay.common.FirmbankingStatus;
import com.mypay.common.WebAdapter;
import com.mypay.firmbanking.application.port.in.ListFirmbankingRequestsCommand;
import com.mypay.firmbanking.application.port.in.ListFirmbankingRequestsQuery;
import com.mypay.firmbanking.domain.FirmbankingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class ListFirmbankingRequestsController {

    private final ListFirmbankingRequestsQuery listFirmbankingRequestsQuery;

    @PostMapping(path = "/banking/firmbanking/membership/{membershipId}")
    ResponseEntity<List<ListFirmbankingRequestResponse>> listFirmbankingRequests(
            @PathVariable String membershipId,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page
    ) {
        ListFirmbankingRequestsCommand command
                = new ListFirmbankingRequestsCommand(membershipId, page);

        return listFirmbankingRequestsQuery
                .listFirmbankingRequests(command).stream()
                .map(this::mapToResponse)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        ResponseEntity::ok
                ));
    }

    private ListFirmbankingRequestResponse mapToResponse(FirmbankingRequest request) {
        return new ListFirmbankingRequestResponse(
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

    public record ListFirmbankingRequestResponse(
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
