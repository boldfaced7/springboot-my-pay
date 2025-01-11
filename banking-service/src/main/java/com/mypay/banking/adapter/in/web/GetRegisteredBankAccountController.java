package com.mypay.banking.adapter.in.web;

import com.mypay.banking.application.port.in.GetRegisteredBankAccountCommand;
import com.mypay.banking.application.port.in.GetRegisteredBankAccountQuery;
import com.mypay.banking.domain.RegisteredBankAccount;
import com.mypay.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class GetRegisteredBankAccountController {

    private final GetRegisteredBankAccountQuery getRegisteredBankAccountQuery;

    @GetMapping(path = "/banking/account/{membershipId}")
    ResponseEntity<GetRegisteredBankAccountResponse> getRegisteredBankAccount(
            @PathVariable String membershipId
    ) {
        GetRegisteredBankAccountCommand command
                = new GetRegisteredBankAccountCommand(membershipId);

        RegisteredBankAccount found
                = getRegisteredBankAccountQuery.getRegisteredBankAccount(command);

        GetRegisteredBankAccountResponse response = mapToResponse(found);
        return ResponseEntity.ok(response);
    }

    private GetRegisteredBankAccountResponse mapToResponse(RegisteredBankAccount account) {
        return new GetRegisteredBankAccountResponse(
                account.getId(),
                account.getMembershipId(),
                account.getBankName(),
                account.getBankAccountNumber(),
                account.isValidLinkedStatus()
        );
    }

    public record GetRegisteredBankAccountResponse(
            String id,
            String membershipId,
            String bankName,
            String bankAccountNumber,
            boolean valid
    ) {}
}
