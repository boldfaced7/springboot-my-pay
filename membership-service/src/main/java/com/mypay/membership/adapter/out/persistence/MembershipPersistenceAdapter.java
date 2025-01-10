package com.mypay.membership.adapter.out.persistence;

import com.mypay.membership.application.port.out.GetMembershipPort;
import com.mypay.membership.application.port.out.RegisterMembershipPort;
import com.mypay.membership.common.PersistenceAdapter;
import com.mypay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter
        implements RegisterMembershipPort, GetMembershipPort {

    private final MembershipJpaRepository membershipJpaRepository;

    @Override
    public Membership createMembership(Membership membership) {
        MembershipJpaEntity membershipJpaEntity = MembershipMapper.mapToJpaEntity(membership);
        MembershipJpaEntity saved = membershipJpaRepository.save(membershipJpaEntity);
        return MembershipMapper.mapToDomain(saved);
    }

    @Override
    public Optional<Membership> findMembershipById(Membership.Id id) {
        return membershipJpaRepository.findById(Long.parseLong(id.value()))
                .map(MembershipMapper::mapToDomain);
    }
}
