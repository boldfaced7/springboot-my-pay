package com.mypay.firmbanking.application.service;

import com.mypay.firmbanking.FirmbankingTestUtil;
import com.mypay.firmbanking.application.port.in.GetFirmbankingRequestCommand;
import com.mypay.firmbanking.domain.FirmbankingRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.mypay.firmbanking.FirmbankingTestUtil.*;

class GetFirmbankingRequestServiceTest {

    @DisplayName("[getFirmbankingRequest] 펌뱅킹 요청 id를 보내면, 펌뱅킹 요청 결과를 반환")
    @Test
    void givenGetFirmbankingRequestCommand_whenRetrieving_thenReturnsFirmbankingRequest() {
        // given
        var dummy = firmbankingRequest();
        var sut = new GetFirmbankingRequestService(id -> Optional.of(dummy));
        var command = new GetFirmbankingRequestCommand(ID);

        // when
        var result = sut.getFirmbankingRequest(command);

        // then
        assertThat(result);
    }

    @DisplayName("[getFirmbankingRequest] 존재하지 않는 펌뱅킹 요청 id를 보내면, 예외를 던짐")
    @Test
    void givenWrongGetFirmbankingRequestCommand_whenRetrieving_thenThrowsException() {
        // given
        var sut = new GetFirmbankingRequestService(id -> Optional.empty());
        var command = new GetFirmbankingRequestCommand(ID);

        // when & then
        assertThrown(() -> sut.getFirmbankingRequest(command), IllegalArgumentException.class);
    }

    private FirmbankingRequest firmbankingRequest() {
        return FirmbankingTestUtil.firmbankingRequest(
                ID, MEMBERSHIP_ID, FROM_BANK_NAME, FROM_BANK_ACCOUNT_NUMBER,
                TO_BANK_NAME, TO_BANK_ACCOUNT_NUMBER, MONEY_AMOUNT,
                SUCCESS, UUID, CREATED_AT, UPDATED_AT, null
        );
    }

    private void assertThat(FirmbankingRequest result) {
        FirmbankingTestUtil.assertThat(result,
                ID, MEMBERSHIP_ID, FROM_BANK_NAME, FROM_BANK_ACCOUNT_NUMBER,
                TO_BANK_NAME, TO_BANK_ACCOUNT_NUMBER, MONEY_AMOUNT,
                SUCCESS, UUID, CREATED_AT, UPDATED_AT, null);
    }
}