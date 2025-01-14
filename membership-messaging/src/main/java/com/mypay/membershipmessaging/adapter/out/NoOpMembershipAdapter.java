package com.mypay.membershipmessaging.adapter.out;

import com.mypay.common.MessagingAdapter;
import com.mypay.membershipmessaging.port.out.GetMembershipPort;
import com.mypay.membershipmessaging.port.out.MembershipRequest;
import com.mypay.membershipmessaging.port.out.MembershipResponse;
import lombok.RequiredArgsConstructor;

@MessagingAdapter
@RequiredArgsConstructor
public class NoOpMembershipAdapter implements GetMembershipPort {

    @Override
    public MembershipResponse getMembership(MembershipRequest request) {
        return new MembershipResponse(request.membershipId(), true);
    }
}
