package com.mypay.membership.application.port.in;

import com.mypay.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class GetMembershipCommand extends SelfValidating<GetMembershipCommand> {
    @NotNull private final String membershipId;

    public GetMembershipCommand(String membershipId) {
        this.membershipId = membershipId;
        this.validateSelf();
    }
}
