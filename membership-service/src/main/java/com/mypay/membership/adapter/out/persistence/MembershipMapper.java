package com.mypay.membership.adapter.out.persistence;

import com.mypay.membership.domain.Membership;

class MembershipMapper {
    public static Membership mapToDomain(MembershipJpaEntity jpaEntity) {
        return Membership.generate(
                new Membership.Id(jpaEntity.getMembershipId().toString()),
                new Membership.Name(jpaEntity.getName()),
                new Membership.Email(jpaEntity.getEmail()),
                new Membership.Address(jpaEntity.getAddress()),
                new Membership.Valid(jpaEntity.isValid()),
                new Membership.Corp(jpaEntity.isCorp()),
                jpaEntity.getCreatedAt(),
                jpaEntity.getUpdatedAt(),
                jpaEntity.getDeletedAt()
        );
    }

    public static MembershipJpaEntity mapToJpaEntity(Membership domainEntity) {
        return new MembershipJpaEntity(
                parseId(domainEntity.getMembershipId()),
                domainEntity.getName(),
                domainEntity.getEmail(),
                domainEntity.getAddress(),
                domainEntity.isValid(),
                domainEntity.isCorp(),
                domainEntity.getCreatedAt(),
                domainEntity.getUpdatedAt(),
                domainEntity.getDeletedAt()
        );
    }

    private static Long parseId(String id) {
        try {
            return Long.parseLong(id);
        } catch (Exception e) {
            return null;
        }
    }
}