package com.mypay.firmbanking.adapter.in.web;

import com.mypay.firmbanking.FirmbankingTestUtil;
import com.mypay.firmbanking.domain.FirmbankingRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.mypay.firmbanking.FirmbankingTestUtil.*;

class GetFirmbankingRequestControllerTest {
    @DisplayName("[getFirmbankingRequest] 펌뱅킹 요청 id를 보내면, 펌뱅킹 요청 결과를 반환")
    @Test
    void givenFirmbankingRequestId_whenRetrieving_thenReturnsFirmbankingRequest() {
        // given
        var dummy = firmbankingRequest();
        var sut = new RequestFirmbankingController(id -> dummy);
        var request = request();

        // when
        var result = sut.requestFirmbanking(request);

        // then
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(result.getBody()).isEqualTo(expected());
    }

    private static RequestFirmbankingController.RequestFirmbankingRequest request() {
        return new RequestFirmbankingController.RequestFirmbankingRequest(
                MEMBERSHIP_ID, FROM_BANK_NAME, FROM_BANK_ACCOUNT_NUMBER,
                TO_BANK_NAME, TO_BANK_ACCOUNT_NUMBER, MONEY_AMOUNT
        );
    }


    private static RequestFirmbankingController.RequestFirmbankingResponse expected() {
        return new RequestFirmbankingController.RequestFirmbankingResponse(
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