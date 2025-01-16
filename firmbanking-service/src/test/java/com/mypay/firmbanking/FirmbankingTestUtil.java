package com.mypay.firmbanking;

import com.mypay.common.FirmbankingStatus;
import com.mypay.firmbanking.adapter.out.persistence.FirmbankingRequestJpaEntity;
import com.mypay.firmbanking.adapter.out.persistence.FirmbankingRequestJpaRepository;
import com.mypay.firmbanking.domain.FirmbankingRequest;
import jakarta.servlet.http.PushBuilder;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FirmbankingTestUtil {
    public final static String ID = "1";
    public final static String MEMBERSHIP_ID = "1";
    public final static String FROM_BANK_NAME = "fromBank";
    public final static String FROM_BANK_ACCOUNT_NUMBER = "1234567890";
    public final static String TO_BANK_NAME = "toBank";
    public final static String TO_BANK_ACCOUNT_NUMBER = "2345678901";
    public final static int MONEY_AMOUNT = 1000;
    public final static FirmbankingStatus IN_PROGRESS = FirmbankingStatus.IN_PROGRESS;
    public final static FirmbankingStatus SUCCESS = FirmbankingStatus.SUCCESS;
    public final static FirmbankingStatus ERROR = FirmbankingStatus.ERROR;
    public final static UUID UUID = java.util.UUID.randomUUID();
    public final static LocalDateTime CREATED_AT = LocalDateTime.MIN;
    public final static LocalDateTime UPDATED_AT = LocalDateTime.MAX;
    public final static LocalDateTime DELETED_AT = LocalDateTime.now();
    public final static int PAGE_NUMBER = 0;
    public final static int PAGE_SIZE = 10;
    public final static boolean VALID = true;
    public final static boolean INVALID = false;

    public static FirmbankingRequest firmbankingRequest(
            String id,
            String membershipId,
            String fromBankName,
            String fromBankAccountNumber,
            String toBankName,
            String toBankAccountNumber,
            int moneyAmount,
            FirmbankingStatus firmbankingStatus,
            UUID uuid,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            LocalDateTime deletedAt
    ) {
        return FirmbankingRequest.generate(
                new FirmbankingRequest.Id(id),
                new FirmbankingRequest.MembershipId(membershipId),
                new FirmbankingRequest.FromBankName(fromBankName),
                new FirmbankingRequest.FromBankAccountNumber(fromBankAccountNumber),
                new FirmbankingRequest.ToBankName(toBankName),
                new FirmbankingRequest.ToBankAccountNumber(toBankAccountNumber),
                new FirmbankingRequest.MoneyAmount(moneyAmount),
                firmbankingStatus,
                uuid,
                createdAt,
                updatedAt,
                deletedAt
        );
    }

    public static void assertThat(
            FirmbankingRequest result,
            String id,
            String membershipId,
            String fromBankName,
            String fromBankAccountNumber,
            String toBankName,
            String toBankAccountNumber,
            int moneyAmount,
            FirmbankingStatus firmbankingStatus,
            UUID uuid,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            LocalDateTime deletedAt

    ) {
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isEqualTo(id);
        Assertions.assertThat(result.getMembershipId()).isEqualTo(membershipId);
        Assertions.assertThat(result.getFromBankName()).isEqualTo(fromBankName);
        Assertions.assertThat(result.getFromBankAccountNumber()).isEqualTo(fromBankAccountNumber);
        Assertions.assertThat(result.getToBankName()).isEqualTo(toBankName);
        Assertions.assertThat(result.getToBankAccountNumber()).isEqualTo(toBankAccountNumber);
        Assertions.assertThat(result.getMoneyAmount()).isEqualTo(moneyAmount);
        Assertions.assertThat(result.getFirmbankingStatus()).isEqualTo(firmbankingStatus);
        Assertions.assertThat(result.getUuid()).isEqualTo(uuid);
        Assertions.assertThat(result.getCreatedAt()).isEqualTo(createdAt);
        Assertions.assertThat(result.getUpdatedAt()).isEqualTo(updatedAt);
        Assertions.assertThat(result.getDeletedAt()).isEqualTo(deletedAt);
    }

    public static FirmbankingRequestJpaRepository mockJpaRepository(
            FirmbankingRequestJpaEntity returnedBySave,
            FirmbankingRequestJpaEntity returnedByFindById,
            List<FirmbankingRequestJpaEntity> returnedByFindByMembershipId
    ) {
        return new FirmbankingRequestJpaRepository() {
            @Override
            public FirmbankingRequestJpaEntity save(FirmbankingRequestJpaEntity firmbankingRequestJpaEntity) {
                return returnedBySave;
            }

            @Override
            public Optional<FirmbankingRequestJpaEntity> findById(Long id) {
                return Optional.ofNullable(returnedByFindById);
            }

            @Override
            public List<FirmbankingRequestJpaEntity> findByMembershipId(String membershipId, Pageable pageable) {
                return returnedByFindByMembershipId;
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
