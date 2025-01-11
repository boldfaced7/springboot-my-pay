package com.mypay.banking.application.service;

import com.mypay.banking.application.port.in.RegisterBankAccountCommand;
import com.mypay.banking.application.port.in.RegisterBankAccountUseCase;
import com.mypay.banking.application.port.out.BankAccountInfoRequest;
import com.mypay.banking.application.port.out.BankAccountInfoResponse;
import com.mypay.banking.application.port.out.RegisterBankAccountPort;
import com.mypay.banking.application.port.out.RequestBankAccountInfoPort;
import com.mypay.banking.domain.RegisteredBankAccount;
import com.mypay.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class RegisterBankAccountService implements RegisterBankAccountUseCase {

    private final RegisterBankAccountPort registerBankAccountPort;
    private final RequestBankAccountInfoPort requestBankAccountInfoPort;

    @Override
    public RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command) {
        // TODO: 계좌 등록 요청 회원 검증

        BankAccountInfoRequest request = new BankAccountInfoRequest(
                command.getBankName(),
                command.getBankAccountNumber()
        );
        BankAccountInfoResponse response
                = requestBankAccountInfoPort.getBankAccountInfo(request);

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
