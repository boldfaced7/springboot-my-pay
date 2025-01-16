package com.mypay.bankaccount;

import com.mypay.bankaccount.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.mypay.bankaccount.adapter.out.persistence.RegisteredBankAccountJpaRepository;
import com.mypay.bankaccount.domain.RegisteredBankAccount;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert;

import java.time.LocalDateTime;
import java.util.Optional;

public class BankAccountTestUtil {
    public static String ID = "1";
    public static String MEMBERSHIP_ID = "1";
    public static String BANK_NAME = "bankName";
    public static String BANK_ACCOUNT_NUMBER = "1234567890";
    public static boolean VALID = true;
    public static boolean INVALID = false;
    public static LocalDateTime CREATED_AT = LocalDateTime.now();
    public static LocalDateTime UPDATED_AT = LocalDateTime.now();
    public static LocalDateTime DELETED_AT = LocalDateTime.now();

    public static RegisteredBankAccount registeredBankAccount(
            String id,
            String membershipId,
            String bankName,
            String bankAccountNumber,
            boolean validLinkedStatus
    ) {
        return RegisteredBankAccount.generate(
                new RegisteredBankAccount.Id(id),
                new RegisteredBankAccount.MembershipId(membershipId),
                new RegisteredBankAccount.BankName(bankName),
                new RegisteredBankAccount.BankAccountNumber(bankAccountNumber),
                new RegisteredBankAccount.ValidLinkedStatus(validLinkedStatus),
                CREATED_AT,
                UPDATED_AT,
                null
        );
    }
    public static RegisteredBankAccount registeredBankAccount(
            String id,
            String membershipId,
            String bankName,
            String bankAccountNumber,
            boolean validLinkedStatus,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            LocalDateTime deletedAt
    ) {
        return RegisteredBankAccount.generate(
                new RegisteredBankAccount.Id(id),
                new RegisteredBankAccount.MembershipId(membershipId),
                new RegisteredBankAccount.BankName(bankName),
                new RegisteredBankAccount.BankAccountNumber(bankAccountNumber),
                new RegisteredBankAccount.ValidLinkedStatus(validLinkedStatus),
                createdAt,
                updatedAt,
                deletedAt
        );
    }

    public static RegisteredBankAccountJpaEntity registeredBankAccountJpaEntity(
            String id,
            String membershipId,
            String bankName,
            String bankAccountNumber,
            boolean validLinkedStatus
    ) {
        return new RegisteredBankAccountJpaEntity(
                Long.parseLong(id),
                membershipId,
                bankName,
                bankAccountNumber,
                validLinkedStatus,
                LocalDateTime.now(),
                LocalDateTime.now(),
                null
        );
    }

    public static RegisteredBankAccountJpaEntity registeredBankAccountJpaEntity(
            String id,
            String membershipId,
            String bankName,
            String bankAccountNumber,
            boolean validLinkedStatus,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            LocalDateTime deletedAt
    ) {
        return new RegisteredBankAccountJpaEntity(
                Long.parseLong(id),
                membershipId,
                bankName,
                bankAccountNumber,
                validLinkedStatus,
                createdAt,
                updatedAt,
                deletedAt
        );
    }

    public static void assertThat(
            RegisteredBankAccount result,
            String id,
            String membershipId,
            String bankName,
            String bankAccountNumber,
            boolean validLinkedStatus
    ) {
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isEqualTo(id);
        Assertions.assertThat(result.getMembershipId()).isEqualTo(membershipId);
        Assertions.assertThat(result.getBankName()).isEqualTo(bankName);
        Assertions.assertThat(result.getBankAccountNumber()).isEqualTo(bankAccountNumber);
        Assertions.assertThat(result.isValidLinkedStatus()).isEqualTo(validLinkedStatus);
        Assertions.assertThat(result.getCreatedAt()).isNotNull();
        Assertions.assertThat(result.getUpdatedAt()).isNotNull();
        Assertions.assertThat(result.getDeletedAt()).isNull();
    }

    public static RegisteredBankAccountJpaRepository mockJpaRepository(
            RegisteredBankAccountJpaEntity returnedBySave,
            RegisteredBankAccountJpaEntity returnedByFindByMembershipId
    ) {
        return new RegisteredBankAccountJpaRepository() {
            @Override
            public RegisteredBankAccountJpaEntity save(RegisteredBankAccountJpaEntity registeredBankAccount) {
                return returnedBySave;
            }

            @Override
            public Optional<RegisteredBankAccountJpaEntity> findByMembershipId(String membershipId) {
                return Optional.ofNullable(returnedByFindByMembershipId);
            }
        };
    }

    public static void assertThrown(ThrowableAssert.ThrowingCallable shouldRaiseThrowable, Class<? extends Throwable> type) {
        Assertions.assertThatThrownBy(shouldRaiseThrowable).isInstanceOf(type);
    }

    public static void assertThrownNothing(ThrowableAssert.ThrowingCallable shouldRaiseThrowable) {
        Assertions.assertThatCode(shouldRaiseThrowable).doesNotThrowAnyException();
    }
}
