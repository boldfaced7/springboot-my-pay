package com.mypay.membership.application.port.out;

import com.mypay.membership.domain.Membership;

public interface RegisterMembershipPort {
    Membership saveMembership(Membership membership);
}
