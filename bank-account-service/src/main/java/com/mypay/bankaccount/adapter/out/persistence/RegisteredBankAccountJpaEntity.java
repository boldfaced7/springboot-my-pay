package com.mypay.bankaccount.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.Function;

@Data
@Entity
@Table(name = "registered_bank_account")
@NoArgsConstructor
@AllArgsConstructor
public class RegisteredBankAccountJpaEntity {

    @Id @GeneratedValue
    private Long id;
    private String membershipId;
    private String bankName;
    private String bankAccountNumber;
    private boolean validLinkedStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public RegisteredBankAccountJpaEntity(
            String membershipId,
            String bankName,
            String bankAccountNumber,
            boolean validLinkedStatus,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            LocalDateTime deletedAt
    ) {
        this.membershipId = membershipId;
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.validLinkedStatus = validLinkedStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public <T> T map(Function<? super RegisteredBankAccountJpaEntity, ? extends T> mapper) {
        Objects.requireNonNull(mapper);
        return mapper.apply(this);
    }

    @Override
    public String toString() {
        return "RegisteredBankAccountJpaEntity{" +
                "id='" + id + '\'' +
                ", membershipId='" + membershipId + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", validLinkedStatus=" + validLinkedStatus +
                '}';
    }
}
