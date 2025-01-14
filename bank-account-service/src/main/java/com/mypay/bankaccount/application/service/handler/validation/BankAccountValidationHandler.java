package com.mypay.bankaccount.application.service.handler.validation;

import com.mypay.bankaccount.application.port.in.RegisterBankAccountCommand;
import com.mypay.bankaccountinformation.port.out.BankAccountInformationRequest;
import com.mypay.bankaccountinformation.port.out.BankAccountInformationResponse;
import com.mypay.bankaccountinformation.port.out.RequestBankAccountInformationPort;
import com.mypay.common.ValidationHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BankAccountValidationHandler implements ValidationHandler<RegisterBankAccountCommand> {

    private final RequestBankAccountInformationPort requestBankAccountInformationPort;

    @Override
    public int getValidationOrder() {
        return ValidationOrder.BANK_ACCOUNT;
    }

    @Override
    public void validate(RegisterBankAccountCommand command) {
        Optional.ofNullable(command)
                .map(this::generateRequest)
                .map(requestBankAccountInformationPort::getBankAccountInfo)
                .filter(BankAccountInformationResponse::valid)
                .orElseThrow(IllegalArgumentException::new);
    }

    private BankAccountInformationRequest generateRequest(RegisterBankAccountCommand command) {
        return new BankAccountInformationRequest(
                command.getBankName(),
                command.getBankAccountNumber()
        );
    }
}
