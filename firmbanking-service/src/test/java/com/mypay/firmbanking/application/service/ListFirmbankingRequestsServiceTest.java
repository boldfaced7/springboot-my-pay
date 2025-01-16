package com.mypay.firmbanking.application.service;

import com.mypay.firmbanking.FirmbankingTestUtil;
import com.mypay.firmbanking.application.port.in.ListFirmbankingRequestsCommand;
import com.mypay.firmbanking.domain.FirmbankingRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.mypay.firmbanking.FirmbankingTestUtil.*;

class ListFirmbankingRequestsServiceTest {

    @DisplayName("[listFirmbankingRequests] 회원 id를 보내면, 펌뱅킹 요청 결과 리스트를 반환")
    @Test
    void givenGetFirmbankingRequestCommand_whenRetrieving_thenReturnsFirmbankingRequests() {
        // given
        var dummy = List.of(firmbankingRequest());
        var sut = new ListFirmbankingRequestsService((id, pageNumber, pageSize) -> dummy);
        var command = new ListFirmbankingRequestsCommand(MEMBERSHIP_ID, PAGE_NUMBER);

        // when
        var results = sut.listFirmbankingRequests(command);

        // then
        results.forEach(this::assertThat);
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
                SUCCESS, UUID, CREATED_AT, UPDATED_AT, null
        );
    }
}