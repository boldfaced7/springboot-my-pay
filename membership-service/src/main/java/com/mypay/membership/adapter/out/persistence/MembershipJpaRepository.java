package com.mypay.membership.adapter.out.persistence;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface MembershipJpaRepository
    extends Repository<MembershipJpaEntity, Long> {
    MembershipJpaEntity save(MembershipJpaEntity membershipJpaEntity);
    Optional<MembershipJpaEntity> findByMembershipId(Long id);
}
