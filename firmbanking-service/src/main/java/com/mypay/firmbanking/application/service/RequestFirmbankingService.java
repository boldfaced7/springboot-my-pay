package com.mypay.firmbanking.application.service;

import com.mypay.common.FirmbankingStatus;
import com.mypay.common.UseCase;
import com.mypay.firmbanking.application.port.in.RequestFirmbankingCommand;
import com.mypay.firmbanking.application.port.in.RequestFirmbankingUseCase;
import com.mypay.externalfirmbanking.port.out.ExternalFirmbankingRequest;
import com.mypay.externalfirmbanking.port.out.ExternalFirmbankingResponse;
import com.mypay.externalfirmbanking.port.out.RequestExternalFirmbankingPort;
import com.mypay.firmbanking.application.port.out.RequestFirmbankingPort;
import com.mypay.firmbanking.domain.FirmbankingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@UseCase
@Transactional
@RequiredArgsConstructor
public class RequestFirmbankingService implements RequestFirmbankingUseCase {

    private final RequestFirmbankingPort requestFirmbankingPort;
    private final RequestExternalFirmbankingPort requestExternalFirmbankingPort;

    @Override
    public FirmbankingRequest requestFirmbanking(RequestFirmbankingCommand command) {
        FirmbankingRequest request = mapToDomain(command);
        FirmbankingRequest saved = requestFirmbankingPort.createFirmbankingRequest(request);

        ExternalFirmbankingRequest externalRequest = mapToExternalRequest(command);
        ExternalFirmbankingResponse externalResponse = requestExternalFirmbankingPort
                .requestExternalFirmbanking(externalRequest);

        FirmbankingRequest externalRequestApplied = saved.updateStatus(externalResponse.status());
        return requestFirmbankingPort.updateFirmbankingRequest(externalRequestApplied);
    }

    private FirmbankingRequest mapToDomain(RequestFirmbankingCommand command) {
        return FirmbankingRequest.generate(
                new FirmbankingRequest.Id(""),
                new FirmbankingRequest.FromBankName(command.getFromBankName()),
                new FirmbankingRequest.FromBankAccountNumber(command.getFromBankAccountNumber()),
                new FirmbankingRequest.ToBankName(command.getToBankName()),
                new FirmbankingRequest.ToBankAccountNumber(command.getToBankAccountNumber()),
                new FirmbankingRequest.MoneyAmount(command.getMoneyAmount()),
                FirmbankingStatus.IN_PROGRESS,
                UUID.randomUUID()
        );
    }

    private ExternalFirmbankingRequest mapToExternalRequest(RequestFirmbankingCommand command) {
        return new ExternalFirmbankingRequest(
                command.getFromBankName(),
                command.getFromBankAccountNumber(),
                command.getToBankName(),
                command.getToBankAccountNumber()
        );
    }
}
