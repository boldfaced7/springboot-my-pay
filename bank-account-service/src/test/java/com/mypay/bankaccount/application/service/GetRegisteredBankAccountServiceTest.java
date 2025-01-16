package com.mypay.bankaccount.application.service;

import com.mypay.bankaccount.application.port.in.GetRegisteredBankAccountCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.mypay.bankaccount.BankAccountTestUtil.*;

class GetRegisteredBankAccountServiceTest {
    @DisplayName("[getRegisteredBankAccount] 유효한 회원 id 입력 시, 연동된 계좌 정보 반환")
    @Test
    void givenMembershipId_whenRetrieving_returnsRegisteredBankAccount() {
        // given
        var dummy = registeredBankAccount(ID, MEMBERSHIP_ID, BANK_NAME, BANK_ACCOUNT_NUMBER, VALID);
        var sut = new GetRegisteredBankAccountService(id -> Optional.of(dummy));
        var command = new GetRegisteredBankAccountCommand(MEMBERSHIP_ID);

        // when
        var result = sut.getRegisteredBankAccount(command);

        // then
        assertThat(result, ID, MEMBERSHIP_ID, BANK_NAME, BANK_ACCOUNT_NUMBER, VALID);
    }

    @DisplayName("[getRegisteredBankAccount] 유효하지 않은 회원 id 입력 시, 예외를 던짐")
    @Test
    void givenWrongMembershipId_whenRetrieving_throwsException() {
        // given
        var sut = new GetRegisteredBankAccountService(id -> Optional.empty());
        var command = new GetRegisteredBankAccountCommand(MEMBERSHIP_ID);

        // when & then
        assertThrown(() -> sut.getRegisteredBankAccount(command), IllegalArgumentException.class);
    }
}