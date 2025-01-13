package com.mypay.membership.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.Function;

@Entity
@Table(name = "membership")
@Data
@NoArgsConstructor
class MembershipJpaEntity {

    @Id
    @GeneratedValue
    private Long membershipId;
    private String name;
    private String address;
    private String email;
    private boolean valid;
    private boolean corp;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;


    public MembershipJpaEntity(
            Long membershipId,
            String name,
            String address,
            String email,
            boolean valid,
            boolean corp
    ) {
        this.membershipId = membershipId;
        this.name = name;
        this.address = address;
        this.email = email;
        this.valid = valid;
        this.corp = corp;
        LocalDateTime.now();
        LocalDateTime.now();
    }

    public <T> T map(Function<? super MembershipJpaEntity, ? extends T> mapper) {
        Objects.requireNonNull(mapper);
        return mapper.apply(this);
    }

    @Override
    public String toString() {
        return "MembershipJpaEntity{" +
                "membershipId=" + membershipId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", isValid=" + valid +
                ", aggregateIdentifier='" + corp + '\'' +
                '}';
    }
}