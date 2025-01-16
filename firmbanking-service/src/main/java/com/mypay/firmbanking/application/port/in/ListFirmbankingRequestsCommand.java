package com.mypay.firmbanking.application.port.in;

import com.mypay.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ListFirmbankingRequestsCommand extends SelfValidating<ListFirmbankingRequestsCommand> {

    @NotBlank private final String membershipId;
    @NotNull private final Integer pageNumber;

    public ListFirmbankingRequestsCommand(String membershipId, Integer pageNumber) {
        this.membershipId = membershipId;
        this.pageNumber = pageNumber;

        this.validateSelf();
    }
}
