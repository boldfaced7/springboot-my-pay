package com.mypay.bankaccount.application.service;

import com.mypay.bankaccountinformation.port.out.BankAccountInformationRequest;
import com.mypay.bankaccountinformation.port.out.BankAccountInformationResponse;
import com.mypay.bankaccount.application.port.out.RegisterBankAccountPort;
import com.mypay.bankaccount.application.port.in.RegisterBankAccountCommand;
import com.mypay.bankaccount.application.port.in.RegisterBankAccountUseCase;
import com.mypay.bankaccountinformation.port.out.RequestBankAccountInformationPort;
import com.mypay.bankaccount.domain.RegisteredBankAccount;
import com.mypay.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class RegisterBankAccountService implements RegisterBankAccountUseCase {

    private final RegisterBankAccountPort registerBankAccountPort;
    private final RequestBankAccountInformationPort requestBankAccountInformationPort;

    @Override
    public RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command) {
        // TODO: 계좌 등록 요청 회원 검증

        BankAccountInformationRequest request = new BankAccountInformationRequest(
                command.getBankName(),
                command.getBankAccountNumber()
        );
        BankAccountInformationResponse response
                = requestBankAccountInformationPort.getBankAccountInfo(request);

        if (response.valid()) {
            RegisteredBankAccount source = mapToDomain(command);
            return registerBankAccountPort.createRegisteredBankAccount(source);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private RegisteredBankAccount mapToDomain(RegisterBankAccountCommand command) {
        return RegisteredBankAccount.generate(
                new RegisteredBankAccount.Id(""),
                new RegisteredBankAccount.MembershipId(command.getMembershipId()),
                new RegisteredBankAccount.BankName(command.getBankName()),
                new RegisteredBankAccount.BankAccountNumber(command.getBankAccountNumber()),
                new RegisteredBankAccount.ValidLinkedStatus(command.isValid())
        );
    }
}
