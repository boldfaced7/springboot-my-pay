package com.mypay.firmbanking.application.service.handler.validation;

import com.mypay.firmbanking.application.port.in.RequestFirmbankingCommand;
import com.mypay.membershipmessaging.port.out.MembershipResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mypay.firmbanking.FirmbankingTestUtil.*;


class MembershipValidationHandlerTest {

    @DisplayName("[getValidationOrder] 검증 순서 조회 시, ValidationOrder의 MEMBERSHIP 반환")
    @Test
    void whenRetrievingValidationOrder_ReturnsValidationOrder() {
        // given
        var sut = new MembershipValidationHandler(request -> null);

        // when
        var result = sut.getValidationOrder();

        // then
        Assertions.assertThat(result).isEqualTo(ValidationOrder.MEMBERSHIP);
    }

    @DisplayName("[validate] 유효한 회원 id 입력 시, 예외를 던지지 않음")
    @Test
    void givenRegisterBankAccountCommand_whenRequesting_returnsNothing() {
        // given
        var dummy = new MembershipResponse(MEMBERSHIP_ID, VALID);
        var sut = new MembershipValidationHandler(request -> dummy);
        var command = requestFirmbankingCommand();

        // when & then
        assertThrownNothing(() -> sut.validate(command));
    }

    @DisplayName("[validate] 유효하지 않은 회원 id 입력 시, 예외를 던짐")
    @Test
    void givenWrongRegisterBankAccountCommand_whenRequesting_throwsException() {
        // given
        var dummy = new MembershipResponse(MEMBERSHIP_ID, INVALID);
        var sut = new MembershipValidationHandler(request -> dummy);
        var command = requestFirmbankingCommand();

        // when & then
        assertThrown(() -> sut.validate(command), IllegalArgumentException.class);
    }

    private RequestFirmbankingCommand requestFirmbankingCommand() {
        return new RequestFirmbankingCommand(
                MEMBERSHIP_ID, FROM_BANK_NAME, FROM_BANK_ACCOUNT_NUMBER,
                TO_BANK_NAME, TO_BANK_ACCOUNT_NUMBER, MONEY_AMOUNT);
    }


}