package com.mypay.membership.adapter.out.persistence;

import com.mypay.membership.application.port.out.RegisterMembershipPort;
import com.mypay.membership.common.PersistenceAdapter;
import com.mypay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements RegisterMembershipPort {

    private final MembershipJpaRepository membershipJpaRepository;

    @Override
    public Membership createMembership(Membership membership) {
        MembershipJpaEntity membershipJpaEntity = MembershipMapper.mapToJpaEntity(membership);
        MembershipJpaEntity saved = membershipJpaRepository.save(membershipJpaEntity);
        return MembershipMapper.mapToDomain(saved);
    }
}
