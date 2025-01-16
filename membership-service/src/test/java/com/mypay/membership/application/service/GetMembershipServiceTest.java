package com.mypay.membership.application.service;

import com.mypay.membership.application.port.in.GetMembershipCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.mypay.membership.MembershipTestUtil.*;

class GetMembershipServiceTest {

    @DisplayName("[getMembership] 회원 id를 입력하면, 해당 회원의 정보를 반환")
    @Test
    void givenMembershipId_whenRetrievingCustomer_thenReturnsMembershipInfo() {
        // given
        var dummy = membership(ID, NAME, EMAIL, ADDRESS, VALID, CORP);
        var sut = new GetMembershipService(id -> Optional.of(dummy));
        var command = new GetMembershipCommand(ID);

        // when
        var result = sut.getMembership(command);

        // then
        assertThat(result, ID, NAME, EMAIL, ADDRESS, VALID, CORP);
    }

    @DisplayName("[getMembership] 존재하지 않는 고객 id를 입력하면, 예외를 던짐")
    @Test
    void givenWrongMembershipId_whenRetrievingMembership_throwsException() {
        // given
        var sut = new GetMembershipService(id -> Optional.empty());
        GetMembershipCommand command = new GetMembershipCommand(ID);

        // When & Then
        assertThrown(() -> sut.getMembership(command), IllegalArgumentException.class);
    }
}