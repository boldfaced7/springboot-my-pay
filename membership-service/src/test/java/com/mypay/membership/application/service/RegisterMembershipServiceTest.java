package com.mypay.membership.application.service;

import com.mypay.membership.application.port.in.RegisterMembershipCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mypay.membership.MembershipTestUtil.*;

class RegisterMembershipServiceTest {
    @DisplayName("[registerMembership] 가입 요청 정보를 입력하면, 가입된 회원의 정보를 반환")
    @Test
    void givenMembershipInfo_whenSigningUp_thenReturnsSignedMembershipInfo() {
        // given
        var dummy = membership(ID, NAME, EMAIL, ADDRESS, VALID, CORP);
        var sut = new RegisterMembershipService(source -> dummy);
        var command = new RegisterMembershipCommand(NAME, EMAIL, ADDRESS, VALID, CORP);

        // when
        var result = sut.registerMembership(command);

        // then
        assertThat(result, ID, NAME, EMAIL, ADDRESS, VALID, CORP);
    }
}