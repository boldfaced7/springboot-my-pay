package com.mypay.bankaccount.application.service;

import com.mypay.bankaccount.application.port.in.RegisterBankAccountCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.mypay.bankaccount.BankAccountTestUtil.*;

class RegisterBankAccountServiceTest {
    @DisplayName("[registerBankAccount] 계좌 정보 입력 시, 연동된 계좌 정보 반환")
    @Test
    void givenRegisterBankAccountCommand_whenRegistering_returnsRegisteredBankAccount() {
        // given
        var dummy = registeredBankAccount(ID, MEMBERSHIP_ID, BANK_NAME, BANK_ACCOUNT_NUMBER, VALID);
        var sut = new RegisterBankAccountService(List.of(), source -> dummy);
        var command = new RegisterBankAccountCommand(MEMBERSHIP_ID, BANK_NAME, BANK_ACCOUNT_NUMBER, VALID);

        // when
        var result = sut.registerBankAccount(command);

        // then
        assertThat(result, ID, MEMBERSHIP_ID, BANK_NAME, BANK_ACCOUNT_NUMBER, VALID);
    }
}