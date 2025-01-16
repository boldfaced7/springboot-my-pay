package com.mypay.firmbanking.adapter.in.web;

import com.mypay.firmbanking.FirmbankingTestUtil;
import com.mypay.firmbanking.domain.FirmbankingRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.List;

import static com.mypay.firmbanking.FirmbankingTestUtil.*;

class ListFirmbankingRequestsControllerTest {
    @DisplayName("[listFirmbankingRequests] 회원 id를 보내면, 펌뱅킹 요청 결과 리스트를 반환")
    @Test
    void givenMembershipIdAndPageNumber_whenRetrieving_thenReturnsFirmbankingRequests() {
        // given
        var dummy = firmbankingRequest();
        var sut = new ListFirmbankingRequestsController(id -> List.of(dummy));

        // when
        var result = sut.listFirmbankingRequests(MEMBERSHIP_ID, PAGE_NUMBER);

        // then
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(result.getBody().isEmpty()).isFalse();
        Assertions.assertThat(result.getBody().get(0)).isEqualTo(expected());
    }

    private static ListFirmbankingRequestsController.ListFirmbankingRequestResponse expected() {
        return new ListFirmbankingRequestsController.ListFirmbankingRequestResponse(
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