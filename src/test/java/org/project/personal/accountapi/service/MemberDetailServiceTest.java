package org.project.personal.accountapi.service;

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
import org.project.personal.accountapi.service.MemberDetailServiceImpl;
import org.project.personal.accountapi.service.MemberService;
import org.project.personal.accountapi.request.MemberDetailModifyRequest;
import org.project.personal.accountapi.utils.MemberTestUtils;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class MemberDetailServiceTest {

    @Mock
    MemberService memberService;

    @Mock
    MemberDetailRepository memberDetailRepository;

    @InjectMocks
    MemberDetailServiceImpl memberDetailService;

    MemberDetail memberDetail;
    Member member;

    @BeforeEach
    void setUP() {
        memberDetail = MemberTestUtils.getMemberDetail();
        member = MemberTestUtils.getMember();
    }

    @Test
    void registerMemberDetail() {
        JoinRequest joinRequest = MemberTestUtils.getJoinRequest();

        given(memberService.registerMember(joinRequest)).willReturn(member);
        given(memberDetailRepository.save(any(MemberDetail.class))).willReturn(memberDetail);

        MemberDetail actual = memberDetailService.registerMemberDetail(joinRequest);

        assertThat(actual.getNickName(), equalTo(joinRequest.getNickName()));

        then(memberDetailRepository)
                .should()
                .save(any(MemberDetail.class));
    }

    @Test
    void searchMemberDetailByMemberId() {
        given(memberDetailService.searchMemberDetailByMemberId(memberDetail.getMemberId())).willReturn(memberDetail);

        MemberDetail actual = memberDetailService.searchMemberDetailByMemberId(memberDetail.getMemberId());

        assertThat(actual.getPhoneNumber(), equalTo(memberDetail.getPhoneNumber()));
    }

    @Test
    void modifyMemberDetailOnMember() {
        MemberDetailModifyRequest memberDetailModifyRequest = MemberTestUtils.getMemberDetailModifyRequest();
        MemberDetail before = MemberTestUtils.getMemberDetail();
        given(memberDetailRepository.findMemberDetailByMemberId(member.getId())).willReturn(memberDetail);

        MemberDetail actual = memberDetailService.modifyMemberDetailOnMember(memberDetail.getMemberId(), memberDetailModifyRequest);

        assertThat(actual.getNickName(), not(equalTo(before.getNickName())));
    }

    @Test
    void deleteDetailByMemberId() {

        willDoNothing().given(memberDetailRepository).deleteMemberDetailByMemberId(member.getId());

        memberDetailService.deleteDetailByMemberId(memberDetail.getMemberId());

        then(memberDetailRepository)
                .should()
                .deleteMemberDetailByMemberId(member.getId());
    }
}