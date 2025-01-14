package com.mypay.firmbanking.adapter.out.persistence;

import com.mypay.firmbanking.domain.FirmbankingRequest;

import java.util.UUID;

public class FirmbankingRequestMapper {

    public static FirmbankingRequest mapToDomain(
            FirmbankingRequestJpaEntity jpaEntity
    ) {
        return FirmbankingRequest.generate(
                new FirmbankingRequest.Id(jpaEntity.getId()+""),
                new FirmbankingRequest.MembershipId(jpaEntity.getMembershipId()),
                new FirmbankingRequest.FromBankName(jpaEntity.getFromBankName()),
                new FirmbankingRequest.FromBankAccountNumber(jpaEntity.getFromBankAccountNumber()),
                new FirmbankingRequest.ToBankName(jpaEntity.getToBankName()),
                new FirmbankingRequest.ToBankAccountNumber(jpaEntity.getToBankAccountNumber()),
                new FirmbankingRequest.MoneyAmount(jpaEntity.getMoneyAmount()),
                jpaEntity.getFirmbankingStatus(),
                UUID.fromString(jpaEntity.getUuid()),
                jpaEntity.getCreatedAt(),
                jpaEntity.getUpdatedAt(),
                jpaEntity.getDeletedAt()
        );
    }

    public static FirmbankingRequestJpaEntity mapToJpaEntity(
            FirmbankingRequest domainEntity
    ) {
        return new FirmbankingRequestJpaEntity(
                parseId(domainEntity.getId()),
                domainEntity.getMembershipId(),
                domainEntity.getFromBankName(),
                domainEntity.getFromBankAccountNumber(),
                domainEntity.getToBankName(),
                domainEntity.getToBankAccountNumber(),
                domainEntity.getMoneyAmount(),
                domainEntity.getFirmbankingStatus(),
                domainEntity.getUuid().toString(),
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
