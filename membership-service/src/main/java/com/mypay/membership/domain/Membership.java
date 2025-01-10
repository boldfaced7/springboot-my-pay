package com.mypay.membership.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Membership {
    private final String membershipId;
    private final String name;
    private final String email;
    private final String address;
    private final boolean valid;
    private final boolean corp;

    public static Membership generate(
            Id id,
            Name name,
            Email email,
            Address address,
            Valid valid,
            Corp corp
    ) {
        return new Membership(
                id.value,
                name.value,
                email.value,
                address.value,
                valid.value,
                corp.value
        );
    }

    public record Id(String value) {}
    public record Name(String value) {}
    public record Email(String value) {}
    public record Address(String value) {}
    public record Valid(boolean value) {}
    public record Corp(boolean value) {}
}
