package com.mypay.membership.application.port.out;

import com.mypay.membership.domain.Membership;

import java.util.Optional;

public interface GetMembershipPort {
    Optional<Membership> findMembershipById(Membership.Id id);
}
