package com.mypay.firmbanking.application.port.in;

import com.mypay.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class RequestFirmbankingCommand
        extends SelfValidating<RequestFirmbankingCommand> {

    @NotBlank private final String fromBankName;
    @NotBlank private final String fromBankAccountNumber;
    @NotBlank private final String toBankName;
    @NotBlank private final String toBankAccountNumber;
    @NotNull private final Integer moneyAmount;

    public RequestFirmbankingCommand(
            String fromBankName,
            String fromBankAccountNumber,
            String toBankName,
            String toBankAccountNumber,
            Integer moneyAmount
    ) {
        this.fromBankName = fromBankName;
        this.fromBankAccountNumber = fromBankAccountNumber;
        this.toBankName = toBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.moneyAmount = moneyAmount;

        this.validateSelf();
    }
}
