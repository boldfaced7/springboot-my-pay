package com.mypay.membership.application.port.in;

import com.mypay.membership.common.UseCase;
import com.mypay.membership.domain.Membership;

@UseCase
public interface UpdateMembershipUseCase {
    Membership updateMembership(UpdateMembershipCommand command);
}
