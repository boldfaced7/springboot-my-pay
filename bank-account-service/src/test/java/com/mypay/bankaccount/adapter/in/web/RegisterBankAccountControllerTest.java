package com.mypay.bankaccount.adapter.in.web;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.mypay.bankaccount.BankAccountTestUtil.*;
import static com.mypay.bankaccount.adapter.in.web.RegisterBankAccountController.RegisterBankAccountRequest;
import static com.mypay.bankaccount.adapter.in.web.RegisterBankAccountController.RegisterBankAccountResponse;

class RegisterBankAccountControllerTest {
    @DisplayName("[registerBankAccount] 계좌 정보 입력 시, 연동된 계좌 정보 반환")
    @Test
    void givenRegisterBankAccountRequest_whenRegistering_returnsRegisteredBankAccount() {
        // given
        var dummy = registeredBankAccount(ID, MEMBERSHIP_ID, BANK_NAME, BANK_ACCOUNT_NUMBER, VALID);
        var sut = new RegisterBankAccountController(request -> dummy);
        var request = new RegisterBankAccountRequest(MEMBERSHIP_ID, BANK_NAME, BANK_ACCOUNT_NUMBER);

        // when
        var result = sut.registerBankAccount(request);

        // then
        var expected = new RegisterBankAccountResponse(
                ID, MEMBERSHIP_ID, BANK_NAME, BANK_ACCOUNT_NUMBER, VALID);

        Assertions.assertThat(result.getBody()).isEqualTo(expected);
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}