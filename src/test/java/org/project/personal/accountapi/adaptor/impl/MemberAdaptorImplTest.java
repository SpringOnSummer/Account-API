package org.project.personal.accountapi.adaptor.impl;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.project.personal.accountapi.dto.request.JoinRequest;
import org.project.personal.accountapi.entity.Member;
import org.project.personal.accountapi.repository.MemberRepository;
import org.project.personal.accountapi.request.MemberPasswordModifyRequest;
import org.project.personal.accountapi.utils.MemberTestUtils;

import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class MemberAdaptorImplTest {

    @InjectMocks
    MemberAdaptorImpl memberAdaptor;

    @Mock
    MemberRepository memberRepository;

    Member member;

    @BeforeEach
    void setUp() {
        member = MemberTestUtils.getMember();
    }

    @Test
    void registerMember() {
        JoinRequest joinRequest = MemberTestUtils.getJoinRequest();

        given(memberRepository.save(any(Member.class))).willReturn(member);

        Member actual = memberAdaptor.registerMember(joinRequest);

        assertAll(
                () -> assertThat(actual, Matchers.notNullValue()),
                () -> assertThat(actual.getPassword(), equalTo(joinRequest.getPassword()))
        );
    }

    @Test
    void searchMemberByMemberId() {

        given(memberRepository.findById(member.getId())).willReturn(Optional.ofNullable(member));

        Member actual = memberAdaptor.searchMemberByMemberId(member.getId());

        assertThat(actual, notNullValue());
    }

    @Test
    void modifyPassword() {

        Member before = MemberTestUtils.getMember();
        MemberPasswordModifyRequest modifyRequest = MemberTestUtils.getMemberPasswordModifyRequest();
        given(memberRepository.findById(member.getId())).willReturn(Optional.ofNullable(member));

        Member actual = memberAdaptor.modifyPassword(member.getId(), modifyRequest);

        assertThat(actual.getPassword(), not(equalTo(before.getPassword())));
    }

    @Test
    void deleteMemberById() {

        willDoNothing().given(memberRepository).deleteById(member.getId());

        memberAdaptor.deleteMemberById(member.getId());

        then(memberRepository)
                .should()
                .deleteById(member.getId());
    }
}