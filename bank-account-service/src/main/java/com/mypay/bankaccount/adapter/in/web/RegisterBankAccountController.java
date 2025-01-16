package com.mypay.bankaccount.adapter.in.web;

import com.mypay.bankaccount.application.port.in.RegisterBankAccountCommand;
import com.mypay.bankaccount.application.port.in.RegisterBankAccountUseCase;
import com.mypay.bankaccount.domain.RegisteredBankAccount;
import com.mypay.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterBankAccountController {

    private final RegisterBankAccountUseCase registerBankAccountUseCase;

    @PostMapping(path = "/banking/account/register")
    ResponseEntity<RegisterBankAccountResponse> registerBankAccount(
            @RequestBody RegisterBankAccountRequest request
    ) {
        RegisterBankAccountCommand command = mapToCommand(request);
        RegisteredBankAccount registered = registerBankAccountUseCase.registerBankAccount(command);
        RegisterBankAccountResponse response = mapToResponse(registered);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    private RegisterBankAccountCommand mapToCommand(RegisterBankAccountRequest request) {
        return new RegisterBankAccountCommand(
                request.membershipId,
                request.bankName,
                request.bankAccountNumber,
                true
        );
    }

    private RegisterBankAccountResponse mapToResponse(RegisteredBankAccount account) {
        return new RegisterBankAccountResponse(
                account.getId(),
                account.getMembershipId(),
                account.getBankName(),
                account.getBankAccountNumber(),
                account.isValidLinkedStatus()
        );
    }

    public record RegisterBankAccountRequest(
            String membershipId,
            String bankName,
            String bankAccountNumber
    ) {}

    public record RegisterBankAccountResponse(
            String id,
            String membershipId,
            String bankName,
            String bankAccountNumber,
            boolean valid
    ) {}
}
