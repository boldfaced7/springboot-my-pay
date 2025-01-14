package com.mypay.bankaccount.application.service.handler.validation;

import com.mypay.bankaccount.application.port.in.RegisterBankAccountCommand;
import com.mypay.common.ValidationHandler;
import com.mypay.membershipmessaging.port.out.GetMembershipPort;
import com.mypay.membershipmessaging.port.out.MembershipRequest;
import com.mypay.membershipmessaging.port.out.MembershipResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MembershipValidationHandler implements ValidationHandler<RegisterBankAccountCommand> {

    private final GetMembershipPort getMembershipPort;

    @Override
    public int getValidationOrder() {
        return ValidationOrder.MEMBERSHIP;
    }

    @Override
    public void validate(RegisterBankAccountCommand command) {
        Optional.ofNullable(command)
                .map(this::generateRequest)
                .map(getMembershipPort::getMembership)
                .filter(MembershipResponse::valid)
                .orElseThrow(IllegalArgumentException::new);
    }

    private MembershipRequest generateRequest(RegisterBankAccountCommand command) {
        return new MembershipRequest(command.getMembershipId());
    }
}
