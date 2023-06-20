package org.project.personal.accountapi.adaptor.impl;

import org.project.personal.accountapi.dto.request.JoinRequest;
import org.project.personal.accountapi.entity.Member;
import org.project.personal.accountapi.entity.MemberDetail;
import org.project.personal.accountapi.repository.MemberDetailRepository;
import org.project.personal.accountapi.request.MemberDetailModifyRequest;
import org.project.personal.accountapi.adaptor.MemberDetailAdaptor;
import org.springframework.stereotype.Component;

@Component
public class MemberDetailAdaptorImpl implements MemberDetailAdaptor {

    private final MemberDetailRepository memberDetailRepository;

    public MemberDetailAdaptorImpl(MemberDetailRepository memberDetailRepository) {
        this.memberDetailRepository = memberDetailRepository;
    }


    @Override
    public MemberDetail registerMemberDetail(Member member, JoinRequest joinRequest) {

        MemberDetail memberDetail = MemberDetail.builder()
                .memberId(member.getId())
                .phoneNumber(joinRequest.getPhoneNumber())
                .nickName(joinRequest.getNickName())
                .build();

        memberDetailRepository.save(memberDetail);

        return memberDetail;
    }

    @Override
    public MemberDetail searchMemberDetailById(Long memberDetailId){
        return memberDetailRepository.findById(memberDetailId).orElseThrow();
    }

    @Override
    public MemberDetail modifyMemberDetailById(Long memberDetailId, MemberDetailModifyRequest memberDetailModifyRequest) {
        MemberDetail memberDetail = memberDetailRepository.findById(memberDetailId).orElseThrow();

        memberDetail.changeNickName(memberDetailModifyRequest.getNickName());
        memberDetail.changePhoneNumber(memberDetailModifyRequest.getPhoneNumber());

        return memberDetail;
    }

    @Override
    public void deleteMemberDetailById(Long memberDetailId) {
        memberDetailRepository.deleteById(memberDetailId);
    }
}

