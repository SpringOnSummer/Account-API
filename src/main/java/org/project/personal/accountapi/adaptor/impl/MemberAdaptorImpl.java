package org.project.personal.accountapi.adaptor.impl;

import org.project.personal.accountapi.adaptor.MemberAdaptor;
import org.project.personal.accountapi.dto.request.JoinRequest;
import org.project.personal.accountapi.dto.request.MemberEmail;
import org.project.personal.accountapi.entity.Member;
import org.project.personal.accountapi.repository.MemberRepository;
import org.project.personal.accountapi.request.MemberPasswordModifyRequest;
import org.project.personal.accountapi.utils.MembersEmailUtils;
import org.springframework.stereotype.Component;

@Component
public class MemberAdaptorImpl implements MemberAdaptor {

    private final MemberRepository memberRepository;

    public MemberAdaptorImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member registerMember(JoinRequest joinRequest) {

        MemberEmail email = MembersEmailUtils.convertEmail(joinRequest.getEmail());

        Member member = Member.builder()
                .memberName(joinRequest.getMemberName())
                .emailLocal(email.getLocal())
                .emailDomain(email.getDomain())
                .password(joinRequest.getPassword())
                .build();

        memberRepository.save(member);

        return member;
    }

    @Override
    public Member searchMemberByMemberId(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow();
    }

    @Override
    public Member modifyPassword(Long memberId, MemberPasswordModifyRequest memberModifyRequest) {

        Member member = memberRepository.findById(memberId).orElseThrow();

        if (memberModifyRequest.getCurrentPassword().matches(member.getPassword())) {
            member.changePassword(memberModifyRequest.getResetPassword());
        }

        return member;
    }

    @Override
    public void deleteMemberById(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
