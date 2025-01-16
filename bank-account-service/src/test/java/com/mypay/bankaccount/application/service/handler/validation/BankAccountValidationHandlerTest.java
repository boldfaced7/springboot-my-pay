package com.mypay.bankaccount.application.service.handler.validation;

import com.mypay.bankaccount.application.port.in.RegisterBankAccountCommand;
import com.mypay.bankaccountinformation.port.out.BankAccountInformationResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mypay.bankaccount.BankAccountTestUtil.*;

class BankAccountValidationHandlerTest {

    @DisplayName("[getValidationOrder] 검증 순서 조회 시, ValidationOrder의 BANK_ACCOUNT 반환")
    @Test
    void whenRetrievingValidationOrder_ReturnsValidationOrder() {
        // given
        var sut = new BankAccountValidationHandler(request -> null);

        // when
        var result = sut.getValidationOrder();

        // then
        Assertions.assertThat(result).isEqualTo(ValidationOrder.BANK_ACCOUNT);
    }

    @DisplayName("[validate] 유효한 계좌 정보 입력 시, 아무 값도 반환하지 않고 예외를 던지지 않음")
    @Test
    void givenRegisterBankAccountCommand_whenRequesting_returnsNothing() {
        // given
        var dummy = new BankAccountInformationResponse(BANK_NAME, BANK_ACCOUNT_NUMBER, VALID);
        var sut = new BankAccountValidationHandler(request -> dummy);
        var command = new RegisterBankAccountCommand(MEMBERSHIP_ID, BANK_NAME, BANK_ACCOUNT_NUMBER, VALID);

        // when & then
        assertThrownNothing(() -> sut.validate(command));
    }

    @DisplayName("[validate] 유효하지 않은 계좌 정보 입력 시, 예외를 던짐")
    @Test
    void givenRegisterBankAccountCommand_whenRequesting_throwsException() {
        // given
        var dummy = new BankAccountInformationResponse(BANK_NAME, BANK_ACCOUNT_NUMBER, INVALID);
        var sut = new BankAccountValidationHandler(request -> dummy);
        var command = new RegisterBankAccountCommand(MEMBERSHIP_ID, BANK_NAME, BANK_ACCOUNT_NUMBER, VALID);

        // when & then
        assertThrown(() -> sut.validate(command), IllegalArgumentException.class);
    }
}