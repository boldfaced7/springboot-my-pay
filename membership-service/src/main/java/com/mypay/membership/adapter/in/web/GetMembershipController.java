package com.mypay.membership.adapter.in.web;

import com.mypay.membership.application.port.in.GetMembershipCommand;
import com.mypay.membership.application.port.in.GetMembershipQuery;
import com.mypay.membership.common.WebAdapter;
import com.mypay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class GetMembershipController {

    private final GetMembershipQuery getMembershipQuery;

    @GetMapping("/membership/{membershipId}")
    ResponseEntity<GetMembershipResponse> getMembership(
            @PathVariable String membershipId
    ) {
        GetMembershipCommand command = mapToCommand(membershipId);
        Membership found = getMembershipQuery.getMembership(command);
        GetMembershipResponse response = mapToResponse(found);

        return ResponseEntity.ok(response);
    }

    private GetMembershipCommand mapToCommand(String membershipId) {
        return GetMembershipCommand.builder()
                .membershipId(membershipId)
                .build();
    }

    private GetMembershipResponse mapToResponse(Membership membership) {
        return new GetMembershipResponse(
                membership.getMembershipId(),
                membership.getName(),
                membership.getEmail(),
                membership.getAddress(),
                membership.isValid(),
                membership.isCorp()
        );
    }

    public record GetMembershipResponse(
            String membershipId,
            String name,
            String email,
            String address,
            boolean valid,
            boolean corp
    ) {}
}
