package com.mypay.firmbanking.application.port.in;

import com.mypay.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class GetFirmbankingRequestCommand
extends SelfValidating<GetFirmbankingRequestCommand> {

    @NotBlank private final String firmbankingRequestId;

    public GetFirmbankingRequestCommand(String firmbankingRequestId) {
        this.firmbankingRequestId = firmbankingRequestId;

        this.validateSelf();
    }
}
