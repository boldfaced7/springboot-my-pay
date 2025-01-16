package com.mypay.firmbanking.application.service;

import com.mypay.common.FirmbankingStatus;
import com.mypay.externalfirmbanking.port.out.ExternalFirmbankingResponse;
import com.mypay.firmbanking.FirmbankingTestUtil;
import com.mypay.firmbanking.application.port.in.RequestFirmbankingCommand;
import com.mypay.firmbanking.domain.FirmbankingRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static com.mypay.firmbanking.FirmbankingTestUtil.*;

class RequestFirmbankingServiceTest {

    @DisplayName("[requestFirmbanking] 펌뱅킹 요청 정보를 보내면, 펌뱅킹 요청 결과(성공)를 반환")
    @Test
    void givenRequestFirmbankingCommand_whenFirmbanking_thenReturnsFirmbankingRequestWithSuccess() {
        // given
        var sut = requestFirmbankingService(SUCCESS);
        var command = requestFirmbankingCommand();

        // when
        var result = sut.requestFirmbanking(command);

        // then
        assertThat(result, SUCCESS, UPDATED_AT);
        Assertions.assertThat(result.getUpdatedAt()).isNotEqualTo(result.getCreatedAt());
    }

    @DisplayName("[requestFirmbanking] 펌뱅킹 요청 정보를 보내면, 펌뱅킹 요청 결과(실패)를 반환")
    @Test
    void givenRequestFirmbankingCommand_whenFirmbanking_thenReturnsFirmbankingRequestWithError() {
        // given
        var sut = requestFirmbankingService(ERROR);
        var command = requestFirmbankingCommand();

        // when
        var result = sut.requestFirmbanking(command);

        // then
        assertThat(result, ERROR, UPDATED_AT);
        Assertions.assertThat(result.getUpdatedAt()).isNotEqualTo(result.getCreatedAt());
    }

    private RequestFirmbankingService requestFirmbankingService(FirmbankingStatus status) {
        return new RequestFirmbankingService(
                request -> firmbankingRequest(IN_PROGRESS, CREATED_AT),
                request -> firmbankingRequest(status, UPDATED_AT),
                request -> new ExternalFirmbankingResponse(status),
                List.of()
        );
    }

    private FirmbankingRequest firmbankingRequest(FirmbankingStatus status, LocalDateTime updatedAt) {
        return FirmbankingTestUtil.firmbankingRequest(
                ID, MEMBERSHIP_ID, FROM_BANK_NAME, FROM_BANK_ACCOUNT_NUMBER,
                TO_BANK_NAME, TO_BANK_ACCOUNT_NUMBER, MONEY_AMOUNT, status, UUID,
                CREATED_AT, updatedAt, null);
    }

    private RequestFirmbankingCommand requestFirmbankingCommand() {
        return new RequestFirmbankingCommand(
                MEMBERSHIP_ID, FROM_BANK_NAME, FROM_BANK_ACCOUNT_NUMBER,
                TO_BANK_NAME, TO_BANK_ACCOUNT_NUMBER, MONEY_AMOUNT
        );
    }

    private void assertThat(FirmbankingRequest result, FirmbankingStatus status, LocalDateTime updatedAt) {
        FirmbankingTestUtil.assertThat(
                result, ID, MEMBERSHIP_ID, FROM_BANK_NAME, FROM_BANK_ACCOUNT_NUMBER,
                TO_BANK_NAME, TO_BANK_ACCOUNT_NUMBER, MONEY_AMOUNT, status, UUID,
                CREATED_AT, updatedAt, null);
    }
}