package org.project.personal.accountapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.project.personal.accountapi.dto.request.JoinRequest;
import org.project.personal.accountapi.entity.Member;
import org.project.personal.accountapi.repository.MemberRepository;
import org.project.personal.accountapi.service.MemberServiceImpl;
import org.project.personal.accountapi.request.MemberPasswordModifyRequest;
import org.project.personal.accountapi.utils.MemberTestUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.*;


@ExtendWith(MockitoExtension.class)
class MemberServiceImplTest {

    @Mock
    MemberRepository memberRepository;

    @InjectMocks
    MemberServiceImpl memberService;

    Member member;
    JoinRequest joinRequest;

    @BeforeEach
    void SetUp() {
        joinRequest = MemberTestUtils.getJoinRequest();
        member = MemberTestUtils.getMember();
    }

    @Test
    void registerMember() {

        given(memberRepository.save(any(Member.class))).willReturn(member);

        Member actual = memberService.registerMember(joinRequest);

        assertThat(actual.getEmailLocal(), equalTo(member.getEmailLocal()));
    }

    @Test
    void searchMemberByMemberId() {
        Member member = MemberTestUtils.getMember();

        given(memberRepository.findById(member.getId())).willReturn(Optional.of(member));

        Member actual = memberService.searchMemberByMemberId(member.getId());

        then(memberRepository)
                .should()
                .findById(member.getId());

    }

    @Test
    void modifyPassword() {
        MemberPasswordModifyRequest request = MemberTestUtils.getMemberPasswordModifyRequest();
        PasswordEncoder tempPasswordEncoder = new BCryptPasswordEncoder();

        given(memberRepository.findById(member.getId())).willReturn(Optional.of(member));

        Member actual = memberService.modifyPassword(member.getId(), request);

        assertThat(actual.getPassword(), equalTo(member.getPassword()));
    }

    @Test
    void deleteMember() {

        willDoNothing().given(memberRepository).deleteById(member.getId());

        memberService.deleteMember(member.getId());

        then(memberRepository)
                .should()
                .deleteById(member.getId());
    }
}