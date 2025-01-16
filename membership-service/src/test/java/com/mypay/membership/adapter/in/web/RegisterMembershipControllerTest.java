package com.mypay.membership.adapter.in.web;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mypay.membership.MembershipTestUtil.*;
import static com.mypay.membership.adapter.in.web.RegisterMembershipController.RegisterMembershipRequest;
import static com.mypay.membership.adapter.in.web.RegisterMembershipController.RegisterMembershipResponse;

class RegisterMembershipControllerTest {
    @DisplayName("[registerMembership] 가입 요청 정보를 입력하면, 가입된 회원의 정보를 반환")
    @Test
    void givenMembershipInfo_whenSigningUp_thenReturnsSignedMembershipInfo() {
        // given
        var dummy = membership(ID, NAME, EMAIL, ADDRESS, VALID, CORP);
        var sut = new RegisterMembershipController(source -> dummy);
        var request = new RegisterMembershipRequest(NAME, EMAIL, ADDRESS, CORP);

        // when
        var result = sut.registerMembership(request).getBody();

        // then
        var expected = new RegisterMembershipResponse(ID, NAME, EMAIL, ADDRESS, VALID, CORP);
        Assertions.assertThat(result).isEqualTo(expected);
    }
}