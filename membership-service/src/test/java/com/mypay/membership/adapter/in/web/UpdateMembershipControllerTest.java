package com.mypay.membership.adapter.in.web;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mypay.membership.MembershipTestUtil.*;
import static com.mypay.membership.adapter.in.web.UpdateMembershipController.*;

class UpdateMembershipControllerTest {
    @DisplayName("[updateMembership] 수정 요청 정보를 입력하면, 수정된 회원의 정보를 반환")
    @Test
    void givenMembershipInfo_whenUpdatingMembership_thenReturnsUpdatedMembershipInfo() {
        // given
        var dummy = membership(ID, NAME, EMAIL, ADDRESS, VALID, CORP);
        var sut = new UpdateMembershipController(source -> dummy);
        var request = new UpdateMembershipRequest(ID, NAME, EMAIL, ADDRESS, VALID, CORP);

        // when
        var result = sut.updateMembership(request).getBody();

        // then
        var expected = new UpdateMembershipResponse(ID, NAME, EMAIL, ADDRESS, VALID, CORP);
        Assertions.assertThat(result).isEqualTo(expected);
    }
}