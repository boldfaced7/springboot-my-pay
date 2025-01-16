package com.mypay.bankaccount.adapter.in.web;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.mypay.bankaccount.BankAccountTestUtil.*;
import static com.mypay.bankaccount.adapter.in.web.GetRegisteredBankAccountController.*;

class GetRegisteredBankAccountControllerTest {

    @DisplayName("[getRegisteredBankAccount] 회원 id 입력 시, 해당 회원의 연동 계좌 정보 반환")
    @Test
    void givenMembershipId_whenRetrieving_thenReturnsGetRegisteredBankAccountResponse() {
        // given
        var dummy = registeredBankAccount(
                ID, MEMBERSHIP_ID, BANK_NAME, BANK_ACCOUNT_NUMBER, VALID);
        var sut = new GetRegisteredBankAccountController(id -> dummy);
        var membershipId = MEMBERSHIP_ID;

        // when
        var result = sut.getRegisteredBankAccount(membershipId);

        // then
        var expected = new GetRegisteredBankAccountResponse(
                ID, MEMBERSHIP_ID, BANK_NAME, BANK_ACCOUNT_NUMBER, VALID);

        Assertions.assertThat(result.getBody()).isEqualTo(expected);
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}