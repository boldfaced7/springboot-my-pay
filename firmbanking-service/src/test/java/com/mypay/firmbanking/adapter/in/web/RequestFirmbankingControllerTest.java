package com.mypay.firmbanking.adapter.in.web;

import com.mypay.firmbanking.FirmbankingTestUtil;
import com.mypay.firmbanking.domain.FirmbankingRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.mypay.firmbanking.FirmbankingTestUtil.*;
import static com.mypay.firmbanking.FirmbankingTestUtil.UPDATED_AT;
import static org.junit.jupiter.api.Assertions.*;

class RequestFirmbankingControllerTest {
    @DisplayName("[requestFirmbanking] 펌뱅킹 요청 정보를 보내면, 펌뱅킹 요청 결과(성공)를 반환")
    @Test
    void givenRequestFirmbankingCommand_whenFirmbanking_thenReturnsFirmbankingRequestWithSuccess() {
        // given
        var dummy = firmbankingRequest();
        var sut = new GetFirmbankingRequestController(id -> dummy);

        // when
        var result = sut.getFirmbankingRequest(ID);

        // then
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(result.getBody()).isEqualTo(expected());
    }

    private static GetFirmbankingRequestController.GetFirmbankingRequestResponse expected() {
        return new GetFirmbankingRequestController.GetFirmbankingRequestResponse(
                ID, MEMBERSHIP_ID, FROM_BANK_NAME, FROM_BANK_ACCOUNT_NUMBER,
                TO_BANK_NAME, TO_BANK_ACCOUNT_NUMBER, MONEY_AMOUNT,
                SUCCESS, UUID);
    }

    private FirmbankingRequest firmbankingRequest() {
        return FirmbankingTestUtil.firmbankingRequest(
                ID, MEMBERSHIP_ID, FROM_BANK_NAME, FROM_BANK_ACCOUNT_NUMBER,
                TO_BANK_NAME, TO_BANK_ACCOUNT_NUMBER, MONEY_AMOUNT,
                SUCCESS, UUID, CREATED_AT, UPDATED_AT, null
        );
    }
}