package com.mypay.membership.application.service;

import com.mypay.membership.application.port.in.GetMembershipCommand;
import com.mypay.membership.application.port.in.GetMembershipQuery;
import com.mypay.membership.application.port.out.GetMembershipPort;
import com.mypay.membership.common.Query;
import com.mypay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

@Query
@RequiredArgsConstructor
public class GetMembershipService implements GetMembershipQuery {

    private final GetMembershipPort getMembershipPort;

    @Override
    public Membership getMembership(GetMembershipCommand command) {
        Membership.Id id = new Membership.Id(command.getMembershipId());
        return getMembershipPort.findMembershipById(id)
                .orElseThrow(IllegalArgumentException::new);
    }
}
