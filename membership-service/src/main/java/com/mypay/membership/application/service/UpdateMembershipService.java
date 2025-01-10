package com.mypay.membership.application.service;

import com.mypay.membership.application.port.in.UpdateMembershipCommand;
import com.mypay.membership.application.port.in.UpdateMembershipUseCase;
import com.mypay.membership.application.port.out.UpdateMembershipPort;
import com.mypay.common.UseCase;
import com.mypay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class UpdateMembershipService implements UpdateMembershipUseCase {

    private final UpdateMembershipPort updateMembershipPort;

    @Override
    public Membership updateMembership(UpdateMembershipCommand command) {
        Membership membership = mapToDomain(command);
        return updateMembershipPort.updateMembership(membership);
    }

    private Membership mapToDomain(UpdateMembershipCommand command) {
        return Membership.generate(
                new Membership.Id(command.getMembershipId()),
                new Membership.Name(command.getName()),
                new Membership.Email(command.getEmail()),
                new Membership.Address(command.getAddress()),
                new Membership.Valid(command.isValid()),
                new Membership.Corp(command.isCorp())
        );
    }
}
