package com.mypay.membership.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.Function;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Membership {
    private final String membershipId;
    private final String name;
    private final String email;
    private final String address;
    private final boolean valid;
    private final boolean corp;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public static Membership generate(
            Name name,
            Email email,
            Address address,
            Valid valid,
            Corp corp
    ) {
        return new Membership(
                null,
                name.value(),
                email.value(),
                address.value(),
                valid.value(),
                corp.value(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                null
        );
    }

    public static Membership generate(
            Id id,
            Name name,
            Email email,
            Address address,
            Valid valid,
            Corp corp,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            LocalDateTime deletedAt
    ) {
        return new Membership(
                id.value(),
                name.value(),
                email.value(),
                address.value(),
                valid.value(),
                corp.value(),
                createdAt,
                updatedAt,
                deletedAt
        );
    }

    public Membership update(Name name, Email email, Address address) {
        return new Membership(
                this.membershipId,
                name.value(),
                email.value(),
                address.value(),
                this.valid,
                this.corp,
                this.createdAt,
                LocalDateTime.now(),
                null
        );
    }

    public Membership delete() {
        return new Membership(
                this.membershipId,
                this.name,
                this.email,
                this.address,
                this.valid,
                this.corp,
                this.createdAt,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    public <T> T map(Function<? super Membership, ? extends T> mapper) {
        Objects.requireNonNull(mapper);
        return mapper.apply(this);
    }

    public record Id(String value) {}
    public record Name(String value) {}
    public record Email(String value) {}
    public record Address(String value) {}
    public record Valid(boolean value) {}
    public record Corp(boolean value) {}
}
