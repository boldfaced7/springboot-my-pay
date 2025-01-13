package com.mypay.externalfirmbanking.port.out;

public record ExternalFirmbankingRequest(
        String fromBankName,
        String fromBankAccountNumber,
        String toBankName,
        String toBankAccountNumber

) {}
