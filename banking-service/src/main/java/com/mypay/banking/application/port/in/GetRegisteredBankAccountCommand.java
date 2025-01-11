package com.mypay.banking.application.port.in;

import com.mypay.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class GetRegisteredBankAccountCommand
        extends SelfValidating<GetRegisteredBankAccountCommand> {

    @NotBlank private final String membershipId;

    public GetRegisteredBankAccountCommand(String membershipId) {
        this.membershipId = membershipId;

        this.validateSelf();
    }
}
