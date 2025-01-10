package com.mypay.membership.application.port.in;

import com.mypay.membership.common.Query;
import com.mypay.membership.domain.Membership;

@Query
public interface GetMembershipQuery {
    Membership getMembership(GetMembershipCommand command);
}
