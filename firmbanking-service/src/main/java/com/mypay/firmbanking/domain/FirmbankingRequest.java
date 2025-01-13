package com.mypay.firmbanking.domain;

import com.mypay.common.FirmbankingStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FirmbankingRequest {
    private final String id;
    private final String fromBankName;
    private final String fromBankAccount;
    private final String toBankName;
    private final String toBankAccount;
    private final int moneyAmount;
    private final FirmbankingStatus firmbankingStatus;
    private final UUID uuid;

    public static FirmbankingRequest generate(
            Id id,
            FromBankName fromBankName,
            FromBankAccountNumber fromBankAccountNumber,
            ToBankName toBankName,
            ToBankAccountNumber toBankAccountNumber,
            MoneyAmount moneyAmount,
            FirmbankingStatus firmbankingStatus,
            UUID uuid
    ) {
        return new FirmbankingRequest(
                id.value(),
                fromBankName.value(),
                fromBankAccountNumber.value(),
                toBankName.value(),
                toBankAccountNumber.value(),
                moneyAmount.value(),
                firmbankingStatus,
                uuid
        );
    }

    public FirmbankingRequest updateStatus(FirmbankingStatus newStatus) {
        return new FirmbankingRequest(
                this.id,
                this.fromBankName,
                this.fromBankAccount,
                this.toBankName,
                this.toBankAccount,
                this.moneyAmount,
                newStatus,
                this.getUuid()
        );
    }

    public record Id(String value) {}
    public record FromBankName(String value) {}
    public record FromBankAccountNumber(String value) {}
    public record ToBankName(String value) {}
    public record ToBankAccountNumber(String value) {}
    public record MoneyAmount(int value) {}
}
