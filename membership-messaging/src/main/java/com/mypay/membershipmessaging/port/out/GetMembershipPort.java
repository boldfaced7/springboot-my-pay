package com.mypay.membershipmessaging.port.out;

public interface GetMembershipPort {
    MembershipResponse getMembership(MembershipRequest request);
}
