package com.mypay.bankaccount.adapter.out.persistence;

import com.mypay.bankaccount.domain.RegisteredBankAccount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.mypay.bankaccount.BankAccountTestUtil.*;

class RegisteredBankAccountPersistenceAdapterTest {

    @DisplayName("[save] 계좌 정보 입력 시, 연동된 계좌 정보 반환")
    @Test
    void givenRegisteredBankAccount_whenSaving_returnsSavedBankAccount() {
        // given
        var dummy = registeredBankAccountJpaEntity(ID, MEMBERSHIP_ID, BANK_NAME, BANK_ACCOUNT_NUMBER, VALID);
        var mocked = mockJpaRepository(dummy, null);
        var sut = new RegisteredBankAccountPersistenceAdapter(mocked);
        var bankAccount = registeredBankAccount(ID, MEMBERSHIP_ID, BANK_NAME, BANK_ACCOUNT_NUMBER, VALID);

        // when
        var result = sut.save(bankAccount);

        // then
        assertThat(result, ID, MEMBERSHIP_ID, BANK_NAME, BANK_ACCOUNT_NUMBER, VALID);
    }

    @DisplayName("[findByMembershipId] 유효한 회원 id 입력 시, 연동된 계좌 정보를 담은 Optional 객체 반환")
    @Test
    void givenMembershipId_whenRetrieving_returnsRegisteredBankAccount() {
        // given
        var dummy = registeredBankAccountJpaEntity(ID, MEMBERSHIP_ID, BANK_NAME, BANK_ACCOUNT_NUMBER, VALID);
        var mocked = mockJpaRepository(null, dummy);
        var sut = new RegisteredBankAccountPersistenceAdapter(mocked);
        var membershipId = new RegisteredBankAccount.MembershipId(ID);

        // when
        var result = sut.findByMembershipId(membershipId);

        // then
        Assertions.assertThat(result.isPresent()).isTrue();
        assertThat(result.get(), ID, MEMBERSHIP_ID, BANK_NAME, BANK_ACCOUNT_NUMBER, VALID);
    }

    @DisplayName("[findByMembershipId] 유효하지 않은 회원 id 입력 시, 빈 Optional 객체 반환")
    @Test
    void givenWrongMembershipId_whenRetrieving_throwsException() {
        // given
        var mocked = mockJpaRepository(null, null);
        var sut = new RegisteredBankAccountPersistenceAdapter(mocked);
        var membershipId = new RegisteredBankAccount.MembershipId(ID);

        // when
        var result = sut.findByMembershipId(membershipId);

        // then
        Assertions.assertThat(result).isEqualTo(Optional.empty());
    }
}