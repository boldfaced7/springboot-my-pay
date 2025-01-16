package com.mypay.membership.application.service;

import com.mypay.membership.application.port.in.UpdateMembershipCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.mypay.membership.MembershipTestUtil.*;

class UpdateMembershipServiceTest {

    @DisplayName("[updateMembership] 수정 요청 정보를 입력하면, 수정된 회원의 정보를 반환")
    @Test
    void givenMembershipInfo_whenUpdatingMembership_thenReturnsUpdatedMembershipInfo() {
        // given
        var dummy = membership(ID, NAME, EMAIL, ADDRESS, VALID, CORP);
        var sut = new UpdateMembershipService(source -> dummy, id -> Optional.of(dummy));
        var command = new UpdateMembershipCommand(ID, NAME, EMAIL, ADDRESS, VALID, CORP);

        // when
        var result = sut.updateMembership(command);

        // then
        assertThat(result, ID, NAME, EMAIL, ADDRESS, VALID, CORP);
    }

    @DisplayName("[updateMembership] 존재하지 않는 고객 id를 입력하면, 예외를 던짐")
    @Test
    void givenWrongMembershipId_whenUpdatingMembership_throwsException() {
        // given
        var sut = new UpdateMembershipService(source -> null, id -> Optional.empty());
        var command = new UpdateMembershipCommand(ID, NAME, EMAIL, ADDRESS, VALID, CORP);

        // When & Then
        assertThrown(() -> sut.updateMembership(command), IllegalArgumentException.class);
    }
}