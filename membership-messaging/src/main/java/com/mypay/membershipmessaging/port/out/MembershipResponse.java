package com.mypay.membershipmessaging.port.out;

public record MembershipResponse(
        String membershipId,
        boolean valid
) {}
