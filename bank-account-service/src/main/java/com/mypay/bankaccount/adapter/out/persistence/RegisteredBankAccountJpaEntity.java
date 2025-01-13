package com.mypay.bankaccount.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public RegisteredBankAccountJpaEntity(
            String membershipId,
            String bankName,
            String bankAccountNumber,
            boolean validLinkedStatus) {
        this.membershipId = membershipId;
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.validLinkedStatus = validLinkedStatus;
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
