package com.mypay.externalfirmbanking.port.out;

public interface RequestExternalFirmbankingPort {
    ExternalFirmbankingResponse requestExternalFirmbanking(
            ExternalFirmbankingRequest request);
}
