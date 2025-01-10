package com.mypay.membership.application.port.in;

import com.mypay.membership.domain.Membership;

public interface GetMembershipQuery {
    Membership getMembership(GetMembershipCommand command);
}
