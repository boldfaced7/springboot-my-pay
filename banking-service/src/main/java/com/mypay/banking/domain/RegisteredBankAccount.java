package com.mypay.banking.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisteredBankAccount {
    private final String id;
    private final String membershipId;
    private final String bankName;
    private final String bankAccountNumber;
    private final boolean validLinkedStatus;

    public static RegisteredBankAccount generate(
            Id id,
            MembershipId membershipId,
            BankName bankName,
            BankAccountNumber bankAccountNumber,
            ValidLinkedStatus validLinkedStatus
    ) {
        return new RegisteredBankAccount(
                id.value,
                membershipId.value,
                bankName.value,
                bankAccountNumber.value,
                validLinkedStatus.value
        );
    }

    public record Id(String value) {}
    public record MembershipId(String value) {}
    public record BankName(String value) {}
    public record BankAccountNumber(String value) {}
    public record ValidLinkedStatus(boolean value) {}
}
