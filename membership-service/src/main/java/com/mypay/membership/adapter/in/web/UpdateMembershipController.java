package com.mypay.membership.adapter.in.web;

import com.mypay.membership.application.port.in.UpdateMembershipCommand;
import com.mypay.membership.application.port.in.UpdateMembershipUseCase;
import com.mypay.common.WebAdapter;
import com.mypay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class UpdateMembershipController {

    private final UpdateMembershipUseCase updateMembershipUseCase;

    @PutMapping("/membership/update")
    ResponseEntity<UpdateMembershipResponse> modifyMembership(
            @RequestBody UpdateMembershipRequest request
    ) {
        UpdateMembershipCommand command = mapToCommand(request);
        Membership registered = updateMembershipUseCase.updateMembership(command);
        UpdateMembershipResponse response = mapToResponse(registered);

        return ResponseEntity.ok(response);
    }

    private UpdateMembershipCommand mapToCommand(UpdateMembershipRequest request) {
        return UpdateMembershipCommand.builder()
                .membershipId(request.membershipId)
                .name(request.name)
                .address(request.address)
                .email(request.email)
                .valid(request.valid)
                .corp(request.corp)
                .build();
    }

    private UpdateMembershipResponse mapToResponse(Membership membership) {
        return new UpdateMembershipResponse(
                membership.getMembershipId(),
                membership.getName(),
                membership.getEmail(),
                membership.getAddress(),
                membership.isValid(),
                membership.isCorp()
        );
    }

    public record UpdateMembershipRequest(
            String membershipId,
            String name,
            String address,
            String email,
            boolean valid,
            boolean corp
    ) {}

    public record UpdateMembershipResponse(
            String membershipId,
            String name,
            String email,
            String address,
            boolean valid,
            boolean corp
    ) {}
}
