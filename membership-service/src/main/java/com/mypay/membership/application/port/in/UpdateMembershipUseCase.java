package com.mypay.membership.application.port.in;

import com.mypay.membership.domain.Membership;

public interface UpdateMembershipUseCase {
    Membership updateMembership(UpdateMembershipCommand command);
}
