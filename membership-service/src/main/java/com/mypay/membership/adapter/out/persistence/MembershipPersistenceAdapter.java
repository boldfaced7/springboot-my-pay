package com.mypay.membership.adapter.out.persistence;

import com.mypay.membership.application.port.out.GetMembershipPort;
import com.mypay.membership.application.port.out.UpdateMembershipPort;
import com.mypay.membership.application.port.out.RegisterMembershipPort;
import com.mypay.membership.common.PersistenceAdapter;
import com.mypay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter
        implements RegisterMembershipPort, GetMembershipPort, UpdateMembershipPort {

    private final MembershipJpaRepository membershipJpaRepository;

    @Override
    public Membership createMembership(Membership membership) {
        MembershipJpaEntity source = MembershipMapper.mapToJpaEntity(membership);
        MembershipJpaEntity saved = membershipJpaRepository.save(source);
        return MembershipMapper.mapToDomain(saved);
    }

    @Override
    public Optional<Membership> findMembershipById(Membership.Id id) {
        return membershipJpaRepository.findById(Long.parseLong(id.value()))
                .map(MembershipMapper::mapToDomain);
    }

    @Override
    public Membership updateMembership(Membership membership) {
        Long targetId = Long.parseLong(membership.getMembershipId());

        MembershipJpaEntity target = membershipJpaRepository.findById(targetId)
                .orElseThrow(IllegalArgumentException::new);
        target.update(MembershipMapper.mapToJpaEntity(membership));

        MembershipJpaEntity updated = membershipJpaRepository.save(target);
        return MembershipMapper.mapToDomain(updated);
    }
}
