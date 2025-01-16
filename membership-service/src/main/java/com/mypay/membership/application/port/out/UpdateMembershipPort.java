package com.mypay.membership.application.port.out;

import com.mypay.membership.domain.Membership;

public interface UpdateMembershipPort {

    Membership updateMembership(Membership membership);
}
