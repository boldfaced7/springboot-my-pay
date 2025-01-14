package com.mypay.firmbanking.application.service.handler.validation;

import com.mypay.common.ValidationHandler;
import com.mypay.firmbanking.application.port.in.RequestFirmbankingCommand;
import com.mypay.membershipmessaging.port.out.GetMembershipPort;
import com.mypay.membershipmessaging.port.out.MembershipRequest;
import com.mypay.membershipmessaging.port.out.MembershipResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MembershipValidationHandler implements ValidationHandler<RequestFirmbankingCommand> {

    private final GetMembershipPort getMembershipPort;

    @Override
    public int getValidationOrder() {
        return ValidationOrder.MEMBERSHIP;
    }

    @Override
    public void validate(RequestFirmbankingCommand command) {
        Optional.ofNullable(command)
                .map(this::generateRequest)
                .map(getMembershipPort::getMembership)
                .filter(MembershipResponse::valid)
                .orElseThrow(IllegalArgumentException::new);
    }

    private MembershipRequest generateRequest(RequestFirmbankingCommand command) {
        return new MembershipRequest(command.getMembershipId());
    }
}
