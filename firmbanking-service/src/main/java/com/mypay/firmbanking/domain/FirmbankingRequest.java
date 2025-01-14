package com.mypay.firmbanking.domain;

import com.mypay.common.FirmbankingStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FirmbankingRequest {
    private final String id;
    private final String membershipId;
    private final String fromBankName;
    private final String fromBankAccountNumber;
    private final String toBankName;
    private final String toBankAccountNumber;
    private final int moneyAmount;
    private final FirmbankingStatus firmbankingStatus;
    private final UUID uuid;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final LocalDateTime deletedAt;

    public static FirmbankingRequest generate(
            MembershipId membershipId,
            FromBankName fromBankName,
            FromBankAccountNumber fromBankAccountNumber,
            ToBankName toBankName,
            ToBankAccountNumber toBankAccountNumber,
            MoneyAmount moneyAmount,
            FirmbankingStatus firmbankingStatus
    ) {
        return new FirmbankingRequest(
                null,
                membershipId.value(),
                fromBankName.value(),
                fromBankAccountNumber.value(),
                toBankName.value(),
                toBankAccountNumber.value(),
                moneyAmount.value(),
                firmbankingStatus,
                UUID.randomUUID(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                null
        );
    }

    public static FirmbankingRequest generate(
            Id id,
            MembershipId membershipId,
            FromBankName fromBankName,
            FromBankAccountNumber fromBankAccountNumber,
            ToBankName toBankName,
            ToBankAccountNumber toBankAccountNumber,
            MoneyAmount moneyAmount,
            FirmbankingStatus firmbankingStatus,
            UUID uuid,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            LocalDateTime deletedAt
    ) {
        return new FirmbankingRequest(
                id.value(),
                membershipId.value(),
                fromBankName.value(),
                fromBankAccountNumber.value(),
                toBankName.value(),
                toBankAccountNumber.value(),
                moneyAmount.value(),
                firmbankingStatus,
                uuid,
                createdAt,
                updatedAt,
                deletedAt
        );
    }

    public FirmbankingRequest updateStatus(FirmbankingStatus newStatus) {
        return new FirmbankingRequest(
                this.id,
                this.membershipId,
                this.fromBankName,
                this.fromBankAccountNumber,
                this.toBankName,
                this.toBankAccountNumber,
                this.moneyAmount,
                newStatus,
                this.uuid,
                this.createdAt,
                LocalDateTime.now(),
                null
        );
    }

    public record Id(String value) {}
    public record MembershipId(String value) {}
    public record FromBankName(String value) {}
    public record FromBankAccountNumber(String value) {}
    public record ToBankName(String value) {}
    public record ToBankAccountNumber(String value) {}
    public record MoneyAmount(int value) {}
}
