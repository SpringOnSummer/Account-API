package org.project.personal.accountapi.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.project.personal.accountapi.adaptor.MemberAdaptor;
import org.project.personal.accountapi.adaptor.MemberDetailAdaptor;
import org.project.personal.accountapi.domain.MemberWithDetail;
import org.project.personal.accountapi.dto.request.JoinRequest;
import org.project.personal.accountapi.dto.response.MemberDataWithDetails;
import org.project.personal.accountapi.entity.Member;
import org.project.personal.accountapi.entity.MemberDetail;
import org.project.personal.accountapi.exception.BadCredentialException;
import org.project.personal.accountapi.request.MemberDetailModifyRequest;
import org.project.personal.accountapi.request.MemberPasswordModifyRequest;
import org.project.personal.accountapi.service.MemberService;
import org.project.personal.accountapi.utils.Behavior;
import org.project.personal.accountapi.utils.BehaviorStatus;
import org.project.personal.accountapi.utils.LogUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberAdaptor memberAdaptor;
    private final MemberDetailAdaptor memberDetailAdaptor;

    public MemberServiceImpl(MemberAdaptor memberAdaptor, MemberDetailAdaptor memberDetailAdaptor) {
        this.memberAdaptor = memberAdaptor;
        this.memberDetailAdaptor = memberDetailAdaptor;
    }

    @Override
    public MemberDataWithDetails registerMember(JoinRequest joinRequest) {

        Member member = memberAdaptor.registerMember(joinRequest);

        MemberDetail memberDetail = memberDetailAdaptor.registerMemberDetail(member, joinRequest);

        return MemberDataWithDetails.builder().member(member).memberDetail(memberDetail).build();
    }

    @Transactional(readOnly = true)
    @Override
    public MemberDataWithDetails searchMemberInformationByEmail(String memberEmail) {
        return memberAdaptor.searchMemberDataByEmail(memberEmail);
    }

    @Override
    public void changePassword(MemberPasswordModifyRequest modifyRequest) {
        Member member = memberAdaptor.searchMemberByEmail(modifyRequest.getTargetEmail());

        if (member.getPassword().matches(modifyRequest.getCurrentPassword())) {
            member.changePassword(modifyRequest.getResetPassword());

            LogUtils.loggingWithTime(Behavior.UPDATE, BehaviorStatus.SUCCESS, "Password", "Member Email", modifyRequest.getTargetEmail());
        } else {
            throw new BadCredentialException();
        }
    }

    @Override
    public void changeMemberDetail(MemberDetailModifyRequest modifyRequest) {

        MemberDetail memberDetail = memberAdaptor.searchMemberDetailByEmail(modifyRequest.getTargetEmail());

        memberDetail.changePhoneNumber(modifyRequest.getPhoneNumber());
        memberDetail.changeNickName(modifyRequest.getNickName());

        LogUtils.loggingWithTime(Behavior.UPDATE, BehaviorStatus.SUCCESS, "Member Detail", "Member Email", modifyRequest.getTargetEmail());

    }

    @Override
    public void deleteMemberByEmail(String targetEmail) {

        MemberWithDetail memberWithDetail = memberAdaptor.searchMemberWithDetailByEmail(targetEmail);

        memberDetailAdaptor.deleteMemberDetailById(memberWithDetail.getMemberDetail().getId());
        memberAdaptor.deleteMemberById(memberWithDetail.getMember().getId());

        LogUtils.loggingWithTime(Behavior.DELETE, BehaviorStatus.SUCCESS, "Member data", "Member Email", targetEmail);
    }

}

