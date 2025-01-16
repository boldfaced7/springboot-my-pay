package com.mypay.membership.adapter.out.persistence;

import com.mypay.membership.domain.Membership;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.mypay.membership.MembershipTestUtil.*;

class MembershipPersistenceAdapterTest {

    @DisplayName("[findMembershipById] 고객 id를 입력하면, 해당 고객의 정보를 담은 Optional을 반환")
    @Test
    void givenMembershipId_whenRetrievingCustomer_thenReturnsMembershipInfo() {
        // given
        var dummy = membershipJpaEntity(ID, NAME, EMAIL, ADDRESS, VALID, CORP);
        var mocked = mockMembershipJpaRepository(null, dummy);
        var sut = new MembershipPersistenceAdapter(mocked);

        // when
        var id = new Membership.Id(ID);
        var result = sut.findMembershipById(id).orElse(null);

        // then
        assertThat(result, ID, NAME, EMAIL, ADDRESS, VALID, CORP);
    }

    @DisplayName("[findMembershipById] 존재하지 않는 고객 id를 입력하면, 빈 Optional을 반환")
    @Test
    void givenWrongMembershipId_whenRetrievingMembership_throwsException() {
        // given
        var mocked = mockMembershipJpaRepository(null, null);
        var sut = new MembershipPersistenceAdapter(mocked);
        var id = new Membership.Id(ID);

        // when
        var result = sut.findMembershipById(id);

        // then
        Assertions.assertThat(result).isEqualTo(Optional.empty());
    }

    @DisplayName("[saveMembership] 가입 요청 정보를 입력하면, 가입된 고객의 정보를 반환")
    @Test
    void givenMembershipInfo_whenSigningUp_thenReturnsSignedMembershipInfo() {
        // given
        var dummy = membershipJpaEntity(ID, NAME, EMAIL, ADDRESS, VALID, CORP);
        var mocked = mockMembershipJpaRepository(dummy, null);
        var sut = new MembershipPersistenceAdapter(mocked);
        var membership = membership(ID, NAME, EMAIL, ADDRESS, VALID, CORP);

        // when
        var result = sut.saveMembership(membership);

        // then
        assertThat(result, ID, NAME, EMAIL, ADDRESS, VALID, CORP);
    }

    @DisplayName("[updateMembership] 수정 요청 정보를 입력하면, 수정된 고객의 정보를 반환")
    @Test
    void givenMembershipInfo_whenUpdatingMembership_thenReturnsUpdatedMembershipInfo() {
        // given
        var dummy = membershipJpaEntity(ID, NAME, EMAIL, ADDRESS, VALID, CORP);
        var mocked = mockMembershipJpaRepository(dummy, null);
        var sut = new MembershipPersistenceAdapter(mocked);
        var membership = membership(ID, NAME, EMAIL, ADDRESS, VALID, CORP);

        // when
        var result = sut.updateMembership(membership);

        // then
        assertThat(result, ID, NAME, EMAIL, ADDRESS, VALID, CORP);
    }
}