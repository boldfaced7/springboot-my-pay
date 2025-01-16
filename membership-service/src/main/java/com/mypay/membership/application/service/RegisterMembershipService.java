package com.mypay.membership.application.service;

import com.mypay.membership.application.port.in.RegisterMembershipCommand;
import com.mypay.membership.application.port.in.RegisterMembershipUseCase;
import com.mypay.membership.application.port.out.RegisterMembershipPort;
import com.mypay.common.UseCase;
import com.mypay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class RegisterMembershipService implements RegisterMembershipUseCase {

    private final RegisterMembershipPort registerMembershipPort;

    @Override
    public Membership registerMembership(RegisterMembershipCommand command) {
        Membership membership = mapToDomain(command);
        return registerMembershipPort.saveMembership(membership);
    }

    private Membership mapToDomain(RegisterMembershipCommand command) {
        return Membership.generate(
                new Membership.Name(command.getName()),
                new Membership.Email(command.getEmail()),
                new Membership.Address(command.getAddress()),
                new Membership.Valid(command.isValid()),
                new Membership.Corp(command.isCorp())
        );
    }
}
