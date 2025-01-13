package com.mypay.firmbanking.adapter.out.persistence;

import com.mypay.common.FirmbankingStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "request_firmbanking")
@NoArgsConstructor
@AllArgsConstructor
public class FirmbankingRequestJpaEntity {

    @Id @GeneratedValue
    private Long id;
    private String fromBankName;
    private  String fromBankAccountNumber;
    private  String toBankName;
    private  String toBankAccountNumber;
    private int moneyAmount;
    private FirmbankingStatus firmbankingStatus;
    private String uuid;

    public FirmbankingRequestJpaEntity(
            String fromBankName,
            String fromBankAccountNumber,
            String toBankName,
            String toBankAccountNumber,
            int moneyAmount,
            FirmbankingStatus firmbankingStatus,
            String uuid
    ) {
        this.fromBankName = fromBankName;
        this.fromBankAccountNumber = fromBankAccountNumber;
        this.toBankName = toBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.moneyAmount = moneyAmount;
        this.firmbankingStatus = firmbankingStatus;
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "FirmbankingRequestJpaEntity{" +
                "id=" + id +
                ", fromBankName='" + fromBankName + '\'' +
                ", fromBankAccountNumber='" + fromBankAccountNumber + '\'' +
                ", toBankName='" + toBankName + '\'' +
                ", toBankAccountNumber='" + toBankAccountNumber + '\'' +
                ", moneyAmount=" + moneyAmount +
                ", firmbankingStatus=" + firmbankingStatus +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
