package com.mypay.membership.adapter.in.web;

import com.mypay.membership.application.port.in.RegisterMembershipCommand;
import com.mypay.membership.application.port.in.RegisterMembershipUseCase;
import com.mypay.membership.common.WebAdapter;
import com.mypay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterMembershipController {

    private final RegisterMembershipUseCase registerMembershipUseCase;

    @PostMapping("/membership/register")
    ResponseEntity<RegisterMembershipResponse> registerMembership(
            @RequestBody RegisterMembershipRequest request
    ) {
        RegisterMembershipCommand command = mapToCommand(request);
        Membership registered = registerMembershipUseCase.registerMembership(command);
        RegisterMembershipResponse response = mapToResponse(registered);

        return ResponseEntity.ok(response);
    }

    private RegisterMembershipCommand mapToCommand(RegisterMembershipRequest request) {
        return RegisterMembershipCommand.builder()
                .name(request.name)
                .address(request.address)
                .email(request.email)
                .valid(true)
                .corp(request.corp)
                .build();
    }

    private RegisterMembershipResponse mapToResponse(Membership membership) {
        return new RegisterMembershipResponse(
                membership.getMembershipId(),
                membership.getName(),
                membership.getEmail(),
                membership.getAddress(),
                membership.isValid(),
                membership.isCorp()
        );
    }

    public record RegisterMembershipRequest(
            String name,
            String address,
            String email,
            boolean corp
    ) {}

    public record RegisterMembershipResponse(
            String membershipId,
            String name,
            String email,
            String address,
            boolean valid,
            boolean corp
    ) {}
}
