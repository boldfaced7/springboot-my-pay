package com.mypay.membership.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "membership")
@Data
@AllArgsConstructor
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

    public MembershipJpaEntity(
            String name,
            String address,
            String email,
            boolean valid,
            boolean corp
    ) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.valid = valid;
        this.corp = corp;
    }

    public void update(MembershipJpaEntity source) {
        this.name = source.name;
        this.address = source.address;
        this.email = source.email;
        this.valid = source.valid;
        this.corp = source.corp;
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