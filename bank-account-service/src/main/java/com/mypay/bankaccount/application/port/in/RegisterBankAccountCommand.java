package com.mypay.bankaccount.application.port.in;

import com.mypay.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class RegisterBankAccountCommand
        extends SelfValidating<RegisterBankAccountCommand> {

    @NotBlank private final String membershipId;
    @NotBlank private final String bankName;
    @NotBlank private final String bankAccountNumber;
    private final boolean valid;

    public RegisterBankAccountCommand(
            String membershipId,
            String bankName,
            String bankAccountNumber,
            boolean valid
    ) {
        this.membershipId = membershipId;
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.valid = valid;

        this.validateSelf();
    }
}
