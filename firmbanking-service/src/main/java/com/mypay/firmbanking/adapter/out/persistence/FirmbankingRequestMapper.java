package com.mypay.firmbanking.adapter.out.persistence;

import com.mypay.firmbanking.domain.FirmbankingRequest;

import java.util.UUID;

public class FirmbankingRequestMapper {

    public static FirmbankingRequest mapToDomain(
            FirmbankingRequestJpaEntity jpaEntity,
            UUID uuid
    ) {
        return FirmbankingRequest.generate(
                new FirmbankingRequest.Id(jpaEntity.getId()+""),
                new FirmbankingRequest.FromBankName(jpaEntity.getFromBankName()),
                new FirmbankingRequest.FromBankAccountNumber(jpaEntity.getFromBankAccountNumber()),
                new FirmbankingRequest.ToBankName(jpaEntity.getToBankName()),
                new FirmbankingRequest.ToBankAccountNumber(jpaEntity.getToBankAccountNumber()),
                new FirmbankingRequest.MoneyAmount(jpaEntity.getMoneyAmount()),
                jpaEntity.getFirmbankingStatus(),
                uuid
        );
    }

    public static FirmbankingRequestJpaEntity mapToJpaEntity(
            FirmbankingRequest domainEntity
    ) {
        return new FirmbankingRequestJpaEntity(
                domainEntity.getFromBankName(),
                domainEntity.getFromBankAccount(),
                domainEntity.getToBankName(),
                domainEntity.getToBankAccount(),
                domainEntity.getMoneyAmount(),
                domainEntity.getFirmbankingStatus(),
                domainEntity.getUuid().toString()
        );
    }

    public static FirmbankingRequestJpaEntity mapToJpaEntity(
            FirmbankingRequest domainEntity,
            String id
    ) {
        return new FirmbankingRequestJpaEntity(
                Long.parseLong(id),
                domainEntity.getFromBankName(),
                domainEntity.getFromBankAccount(),
                domainEntity.getToBankName(),
                domainEntity.getToBankAccount(),
                domainEntity.getMoneyAmount(),
                domainEntity.getFirmbankingStatus(),
                domainEntity.getUuid().toString()
        );
    }
}
