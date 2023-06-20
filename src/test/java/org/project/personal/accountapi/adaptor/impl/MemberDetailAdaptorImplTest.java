package org.project.personal.accountapi.adaptor.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.project.personal.accountapi.dto.request.JoinRequest;
import org.project.personal.accountapi.entity.Member;
import org.project.personal.accountapi.entity.MemberDetail;
import org.project.personal.accountapi.repository.MemberDetailRepository;
import org.project.personal.accountapi.request.MemberDetailModifyRequest;
import org.project.personal.accountapi.utils.MemberTestUtils;

import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class MemberDetailAdaptorImplTest {

    @InjectMocks
    MemberDetailAdaptorImpl memberDetailAdaptor;

    @Mock
    MemberDetailRepository memberDetailRepository;

    MemberDetail memberDetail;

    @BeforeEach
    void setUp() {

        memberDetail = MemberTestUtils.getMemberDetail();

    }

    @Test
    void registerMemberDetail() {

        Member member = MemberTestUtils.getMember();
        JoinRequest joinRequest = MemberTestUtils.getJoinRequest();

        MemberDetail actual = memberDetailAdaptor.registerMemberDetail(member, joinRequest);

        assertAll(
                () -> assertThat(actual.getPhoneNumber(), notNullValue()),
                () -> assertThat(actual.getPhoneNumber(), equalTo(memberDetail.getPhoneNumber()))
        );
    }

    @Test
    void searchMemberDetailById() {

        given(memberDetailRepository.findById(memberDetail.getMemberId())).willReturn(Optional.ofNullable(memberDetail));

        MemberDetail actual = memberDetailAdaptor.searchMemberDetailById(memberDetail.getMemberId());

        assertThat(actual.getPhoneNumber(), equalTo(memberDetail.getPhoneNumber()));
    }

    @Test
    void modifyMemberDetailById() {

        MemberDetailModifyRequest modifyRequest = MemberTestUtils.getMemberDetailModifyRequest();
        MemberDetail before = MemberTestUtils.getMemberDetail();

        given(memberDetailRepository.findById(memberDetail.getMemberId())).willReturn(Optional.ofNullable(memberDetail));

        MemberDetail actual = memberDetailAdaptor.modifyMemberDetailById(memberDetail.getMemberId(), modifyRequest);

        assertThat(actual.getPhoneNumber(), not(equalTo(before.getPhoneNumber())));
    }

    @Test
    void deleteMemberDetailById() {

        willDoNothing().given(memberDetailRepository).deleteById(memberDetail.getMemberId());

        memberDetailAdaptor.deleteMemberDetailById(memberDetail.getMemberId());

        then(memberDetailRepository)
                .should()
                .deleteById(memberDetail.getMemberId());
    }
}