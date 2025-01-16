package com.mypay.membership.adapter.out.persistence;

import com.mypay.membership.application.port.out.GetMembershipPort;
import com.mypay.membership.application.port.out.UpdateMembershipPort;
import com.mypay.membership.application.port.out.RegisterMembershipPort;
import com.mypay.common.PersistenceAdapter;
import com.mypay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter
        implements RegisterMembershipPort, GetMembershipPort, UpdateMembershipPort {

    private final MembershipJpaRepository membershipJpaRepository;

    @Override
    public Membership saveMembership(Membership membership) {
        return membership
                .map(MembershipMapper::mapToJpaEntity)
                .map(membershipJpaRepository::save)
                .map(MembershipMapper::mapToDomain);
    }

    @Override
    public Optional<Membership> findMembershipById(Membership.Id id) {
        return membershipJpaRepository.findByMembershipId(Long.parseLong(id.value()))
                .map(MembershipMapper::mapToDomain);
    }

    @Override
    public Membership updateMembership(Membership membership) {
        return membership
                .map(MembershipMapper::mapToJpaEntity)
                .map(membershipJpaRepository::save)
                .map(MembershipMapper::mapToDomain);
    }
}
