package com.mypay.membership.adapter.in.web;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mypay.membership.MembershipTestUtil.*;
import static com.mypay.membership.adapter.in.web.GetMembershipController.GetMembershipResponse;

class GetMembershipControllerTest {
    @DisplayName("[getMembership] 회원 id를 입력하면, 해당 회원의 정보를 반환")
    @Test
    void givenMembershipId_whenRetrievingCustomer_thenReturnsMembershipInfo() {
        // given
        var dummy = membership(ID, NAME, EMAIL, ADDRESS, VALID, CORP);
        var sut = new GetMembershipController(id -> dummy);

        // when
        var result = sut.getMembership(ID).getBody();

        // then
        var expected = new GetMembershipResponse(ID, NAME, EMAIL, ADDRESS, VALID, CORP);
        Assertions.assertThat(result).isEqualTo(expected);
    }
}