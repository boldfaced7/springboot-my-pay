package com.mypay.firmbanking.adapter.out.persistence;

import com.mypay.common.FirmbankingStatus;
import com.mypay.firmbanking.FirmbankingTestUtil;
import com.mypay.firmbanking.domain.FirmbankingRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static com.mypay.firmbanking.FirmbankingTestUtil.*;

class FirmbankingRequestPersistenceAdapterTest {

    @DisplayName("저장할 펌뱅킹 요청 정보를 전달하면, 저장된 펌뱅킹 요청 정보를 반환")
    @Test
    void givenFirmbankingRequest_whenSaving_thenReturnsSavedFirmbankingRequest() {
        // given
        var dummy = firmbankingRequestJpaEntity(IN_PROGRESS, CREATED_AT);
        var mocked = FirmbankingTestUtil.mockJpaRepository(dummy, dummy, List.of());
        var sut = new FirmbankingRequestPersistenceAdapter(mocked);
        var request = firmbankingRequest(IN_PROGRESS, CREATED_AT);

        // when
        var result = sut.saveFirmbankingRequest(request);

        // then
        assertThat(result, IN_PROGRESS, CREATED_AT);
    }

    @DisplayName("갱신할 펌뱅킹 요청 정보를 전달하면, 갱신된 펌뱅킹 요청 정보를 반환")
    @Test
    void givenFirmbankingRequest_whenUpdating_thenReturnsUpdatedFirmbankingRequest() {
        // given
        var dummy = firmbankingRequestJpaEntity(SUCCESS, UPDATED_AT);
        var mocked = FirmbankingTestUtil.mockJpaRepository(dummy, dummy, List.of());
        var sut = new FirmbankingRequestPersistenceAdapter(mocked);
        var request = firmbankingRequest(SUCCESS, UPDATED_AT);

        // when
        var result = sut.updateFirmbankingRequest(request);

        // then
        assertThat(result, SUCCESS, UPDATED_AT);
    }

    @DisplayName("조회할 펌뱅킹 요청 id를 전달하면, 펌뱅킹 요청 정보를 반환")
    @Test
    void givenFirmbankingRequestId_whenRetrieving_thenReturnsFirmbankingRequest() {
        // given
        var dummy = firmbankingRequestJpaEntity(SUCCESS, UPDATED_AT);
        var mocked = FirmbankingTestUtil.mockJpaRepository(dummy, dummy, List.of());
        var sut = new FirmbankingRequestPersistenceAdapter(mocked);
        var id = new FirmbankingRequest.Id(ID);

        // when
        var result = sut.findById(id);

        // then
        Assertions.assertThat(result.isPresent()).isTrue();
        assertThat(result.get(), SUCCESS, UPDATED_AT);
    }

    @DisplayName("유효하지 않은 펌뱅킹 요청 id를 전달하면, 예외를 던짐")
    @Test
    void givenFirmbankingRequestId_whenRetrieving_thenThrowsException() {
        // given
        var mocked = FirmbankingTestUtil.mockJpaRepository(null, null, List.of());
        var sut = new FirmbankingRequestPersistenceAdapter(mocked);
        var id = new FirmbankingRequest.Id(ID);

        // when
        var result = sut.findById(id);

        // then
        Assertions.assertThat(result.isEmpty()).isTrue();
    }

    @DisplayName("조회할 회원 id와 페이지 넘버를 전달하면, 펌뱅킹 요청 정보 리스트를 반환")
    @Test
    void givenMembershipIdAndPageNumber_whenRetrieving_thenReturnsFirmbankingRequests() {
        // given
        var dummy = firmbankingRequestJpaEntity(SUCCESS, UPDATED_AT);
        var mocked = FirmbankingTestUtil.mockJpaRepository(dummy, dummy, List.of(dummy));
        var sut = new FirmbankingRequestPersistenceAdapter(mocked);
        var membershipId = new FirmbankingRequest.MembershipId(MEMBERSHIP_ID);

        // when
        var results = sut.listByMembershipId(membershipId, PAGE_NUMBER, PAGE_SIZE);

        // then
        results.forEach(result -> assertThat(result, SUCCESS, UPDATED_AT));
        Assertions.assertThat(results.size()).isLessThanOrEqualTo(PAGE_SIZE);
    }


    private FirmbankingRequestJpaEntity firmbankingRequestJpaEntity(FirmbankingStatus status, LocalDateTime updatedAt) {
        return new FirmbankingRequestJpaEntity(
                Long.parseLong(ID), MEMBERSHIP_ID, FROM_BANK_NAME, FROM_BANK_ACCOUNT_NUMBER,
                TO_BANK_NAME, TO_BANK_ACCOUNT_NUMBER, MONEY_AMOUNT, status, UUID.toString(),
                CREATED_AT, updatedAt, null
        );
    }

    private FirmbankingRequest firmbankingRequest(FirmbankingStatus status, LocalDateTime updatedAt) {
        return FirmbankingTestUtil.firmbankingRequest(
                ID, MEMBERSHIP_ID, FROM_BANK_NAME, FROM_BANK_ACCOUNT_NUMBER,
                TO_BANK_NAME, TO_BANK_ACCOUNT_NUMBER, MONEY_AMOUNT, status, UUID,
                CREATED_AT, updatedAt, null);
    }
    private void assertThat(FirmbankingRequest result, FirmbankingStatus status, LocalDateTime updatedAt) {
        FirmbankingTestUtil.assertThat(
                result, ID, MEMBERSHIP_ID, FROM_BANK_NAME, FROM_BANK_ACCOUNT_NUMBER,
                TO_BANK_NAME, TO_BANK_ACCOUNT_NUMBER, MONEY_AMOUNT, status, UUID,
                CREATED_AT, updatedAt, null);
    }
}