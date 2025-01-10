package com.mypay.membership.adapter.out.persistence;

import com.mypay.membership.domain.Membership;

class MembershipMapper {
    public static Membership mapToDomain(MembershipJpaEntity membership) {
        return Membership.generate(
                new Membership.Id(membership.getMembershipId()+""),
                new Membership.Name(membership.getName()),
                new Membership.Email(membership.getEmail()),
                new Membership.Address(membership.getAddress()),
                new Membership.Valid(membership.isValid()),
                new Membership.Corp(membership.isCorp())
        );
    }

    public static MembershipJpaEntity mapToJpaEntity(Membership membership) {
        return new MembershipJpaEntity(
                membership.getName(),
                membership.getEmail(),
                membership.getAddress(),
                membership.isValid(),
                membership.isCorp()
        );
    }
}