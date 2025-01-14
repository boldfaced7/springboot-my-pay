package com.mypay.bankaccount.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.Function;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisteredBankAccount {
    private final String id;
    private final String membershipId;
    private final String bankName;
    private final String bankAccountNumber;
    private final boolean validLinkedStatus;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final LocalDateTime deletedAt;

    public static RegisteredBankAccount generate(
            MembershipId membershipId,
            BankName bankName,
            BankAccountNumber bankAccountNumber,
            ValidLinkedStatus validLinkedStatus
    ) {
        return new RegisteredBankAccount(
                null,
                membershipId.value(),
                bankName.value(),
                bankAccountNumber.value(),
                validLinkedStatus.value(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                null
        );
    }

    public static RegisteredBankAccount generate(
            Id id,
            MembershipId membershipId,
            BankName bankName,
            BankAccountNumber bankAccountNumber,
            ValidLinkedStatus validLinkedStatus,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            LocalDateTime deletedAt
    ) {
        return new RegisteredBankAccount(
                id.value(),
                membershipId.value(),
                bankName.value(),
                bankAccountNumber.value(),
                validLinkedStatus.value(),
                createdAt,
                updatedAt,
                deletedAt
        );
    }

    public RegisteredBankAccount update(
            BankName bankName,
            BankAccountNumber bankAccountNumber
    ) {
        return new RegisteredBankAccount(
                id,
                membershipId,
                bankName.value(),
                bankAccountNumber.value(),
                validLinkedStatus,
                createdAt,
                LocalDateTime.now(),
                null
        );
    }

    public RegisteredBankAccount delete() {
        return new RegisteredBankAccount(
                id,
                membershipId,
                bankName,
                bankAccountNumber,
                validLinkedStatus,
                createdAt,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    public <T> T map(Function<? super RegisteredBankAccount, ? extends T> mapper) {
        Objects.requireNonNull(mapper);
        return mapper.apply(this);
    }

    public record Id(String value) {}
    public record MembershipId(String value) {}
    public record BankName(String value) {}
    public record BankAccountNumber(String value) {}
    public record ValidLinkedStatus(boolean value) {}
}
