package com.mypay.membership;

import com.mypay.membership.adapter.out.persistence.MembershipJpaEntity;
import com.mypay.membership.adapter.out.persistence.MembershipJpaRepository;
import com.mypay.membership.domain.Membership;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert;

import java.time.LocalDateTime;
import java.util.Optional;

public class MembershipTestUtil {

    public static String ID = "1";
    public static String NAME = "NAME";
    public static String EMAIL = "EMAIL";
    public static String ADDRESS = "ADDRESS";
    public static boolean VALID = true;
    public static boolean CORP = true;
    public static LocalDateTime CREATED_AT = LocalDateTime.now();
    public static LocalDateTime UPDATED_AT = LocalDateTime.now();
    public static LocalDateTime DELETED_AT = LocalDateTime.now();

    public static Membership membership(
            String id,
            String name,
            String email,
            String address,
            boolean valid,
            boolean corp
    ) {
        return Membership.generate(
                new Membership.Id(id),
                new Membership.Name(name),
                new Membership.Email(email),
                new Membership.Address(address),
                new Membership.Valid(valid),
                new Membership.Corp(corp),
                LocalDateTime.now(),
                LocalDateTime.now(),
                null
        );
    }
    public static Membership membership(
            String id,
            String name,
            String email,
            String address,
            boolean valid,
            boolean corp,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            LocalDateTime deletedAt
    ) {
        return Membership.generate(
                new Membership.Id(id),
                new Membership.Name(name),
                new Membership.Email(email),
                new Membership.Address(address),
                new Membership.Valid(valid),
                new Membership.Corp(corp),
                createdAt,
                updatedAt,
                deletedAt
        );
    }

    public static MembershipJpaEntity membershipJpaEntity(
            String id,
            String name,
            String email,
            String address,
            boolean valid,
            boolean corp,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            LocalDateTime deletedAt

    ) {
        return new MembershipJpaEntity(
                Long.parseLong(id),
                name,
                email,
                address,
                valid,
                corp,
                createdAt,
                updatedAt,
                deletedAt
        );
    }

    public static MembershipJpaEntity membershipJpaEntity(
            String id,
            String name,
            String email,
            String address,
            boolean valid,
            boolean corp
    ) {
        return new MembershipJpaEntity(
                Long.parseLong(id),
                name,
                email,
                address,
                valid,
                corp,
                LocalDateTime.now(),
                LocalDateTime.now(),
                null
        );
    }

    public static MembershipJpaRepository mockMembershipJpaRepository(
            MembershipJpaEntity returnedBySave,
            MembershipJpaEntity returnedByFindByMembershipId) {
        return new MembershipJpaRepository() {
            @Override
            public MembershipJpaEntity save(MembershipJpaEntity membershipJpaEntity) {
                return returnedBySave;
            }

            @Override
            public Optional<MembershipJpaEntity> findByMembershipId(Long id) {
                return Optional.ofNullable(returnedByFindByMembershipId);
            }
        };
    }

    public static void assertThat(Membership result, String id, String name, String email, String address, boolean valid, boolean corp) {
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getMembershipId()).isEqualTo(id);
        Assertions.assertThat(result.getName()).isEqualTo(name);
        Assertions.assertThat(result.getEmail()).isEqualTo(email);
        Assertions.assertThat(result.getAddress()).isEqualTo(address);
        Assertions.assertThat(result.isValid()).isEqualTo(valid);
        Assertions.assertThat(result.isCorp()).isEqualTo(corp);
        Assertions.assertThat(result.getCreatedAt()).isNotNull();
        Assertions.assertThat(result.getUpdatedAt()).isNotNull();
        Assertions.assertThat(result.getDeletedAt()).isNull();
    }

    public static void assertThrown(ThrowableAssert.ThrowingCallable shouldRaiseThrowable, Class<? extends Throwable> type) {
        Assertions.assertThatThrownBy(shouldRaiseThrowable).isInstanceOf(type);
    }
}
