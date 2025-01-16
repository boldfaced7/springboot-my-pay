package com.mypay.firmbanking.application.service;

import com.mypay.common.FirmbankingStatus;
import com.mypay.common.UseCase;
import com.mypay.common.ValidationHandler;
import com.mypay.firmbanking.application.port.in.RequestFirmbankingCommand;
import com.mypay.firmbanking.application.port.in.RequestFirmbankingUseCase;
import com.mypay.externalfirmbanking.port.out.ExternalFirmbankingRequest;
import com.mypay.externalfirmbanking.port.out.RequestExternalFirmbankingPort;
import com.mypay.firmbanking.application.port.out.RegisterRequestFirmbankingPort;
import com.mypay.firmbanking.application.port.out.UpdateRequestFirmbankingPort;
import com.mypay.firmbanking.domain.FirmbankingRequest;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@UseCase
@Transactional
@RequiredArgsConstructor
public class RequestFirmbankingService implements RequestFirmbankingUseCase {

    private final RegisterRequestFirmbankingPort registerRequestFirmbankingPort;
    private final UpdateRequestFirmbankingPort updateRequestFirmbankingPort;
    private final RequestExternalFirmbankingPort requestExternalFirmbankingPort;
    private final List<ValidationHandler<RequestFirmbankingCommand>> validationHandlers;

    @PostConstruct
    private void init() {
        validationHandlers.sort(Comparator.comparingInt(ValidationHandler::getValidationOrder));
    }

    @Override
    public FirmbankingRequest requestFirmbanking(RequestFirmbankingCommand command) {
        validationHandlers.forEach(handler -> handler.validate(command));

        FirmbankingRequest pending = savePendingRequest(command);
        FirmbankingRequest done = requestExternalFirmbanking(pending);

        return updateRequestFirmbankingPort.updateFirmbankingRequest(done);
    }

    private FirmbankingRequest savePendingRequest(RequestFirmbankingCommand command) {
        FirmbankingRequest source = FirmbankingRequest.generate(
                new FirmbankingRequest.MembershipId(command.getMembershipId()),
                new FirmbankingRequest.FromBankName(command.getFromBankName()),
                new FirmbankingRequest.FromBankAccountNumber(command.getFromBankAccountNumber()),
                new FirmbankingRequest.ToBankName(command.getToBankName()),
                new FirmbankingRequest.ToBankAccountNumber(command.getToBankAccountNumber()),
                new FirmbankingRequest.MoneyAmount(command.getMoneyAmount()),
                FirmbankingStatus.IN_PROGRESS
        );
        return registerRequestFirmbankingPort.saveFirmbankingRequest(source);
    }

    private FirmbankingRequest requestExternalFirmbanking(FirmbankingRequest source) {
        ExternalFirmbankingRequest request = new ExternalFirmbankingRequest(
                source.getFromBankName(),
                source.getFromBankAccountNumber(),
                source.getToBankName(),
                source.getToBankAccountNumber()
        );
        FirmbankingStatus newStatus = requestExternalFirmbankingPort
                .requestExternalFirmbanking(request).status();

        return source.updateStatus(newStatus);
    }
}
