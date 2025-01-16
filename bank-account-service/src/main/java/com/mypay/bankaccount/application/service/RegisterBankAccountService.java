package com.mypay.bankaccount.application.service;

import com.mypay.common.ValidationHandler;
import com.mypay.bankaccount.application.port.out.RegisterBankAccountPort;
import com.mypay.bankaccount.application.port.in.RegisterBankAccountCommand;
import com.mypay.bankaccount.application.port.in.RegisterBankAccountUseCase;
import com.mypay.bankaccount.domain.RegisteredBankAccount;
import com.mypay.common.UseCase;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@UseCase
@Transactional
@RequiredArgsConstructor
public class RegisterBankAccountService implements RegisterBankAccountUseCase {

    private final List<ValidationHandler<RegisterBankAccountCommand>> validationHandlers;
    private final RegisterBankAccountPort registerBankAccountPort;

    @PostConstruct
    private void init() {
        validationHandlers.sort(Comparator.comparingInt(ValidationHandler::getValidationOrder));
    }

    @Override
    public RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command) {
        validationHandlers.forEach(handler -> handler.validate(command));
        RegisteredBankAccount source = mapToDomain(command);
        return registerBankAccountPort.save(source);
    }

    private RegisteredBankAccount mapToDomain(RegisterBankAccountCommand command) {
        return RegisteredBankAccount.generate(
                new RegisteredBankAccount.MembershipId(command.getMembershipId()),
                new RegisteredBankAccount.BankName(command.getBankName()),
                new RegisteredBankAccount.BankAccountNumber(command.getBankAccountNumber()),
                new RegisteredBankAccount.ValidLinkedStatus(command.isValid())
        );
    }
}
