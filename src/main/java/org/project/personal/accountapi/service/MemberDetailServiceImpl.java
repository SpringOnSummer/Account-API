package org.project.personal.accountapi.service;

import org.project.personal.accountapi.dto.request.JoinRequest;
import org.project.personal.accountapi.entity.MemberDetail;
import org.project.personal.accountapi.repository.MemberDetailRepository;
import org.project.personal.accountapi.request.MemberDetailModifyRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class MemberDetailServiceImpl implements MemberDetailService{

    private final MemberDetailRepository memberDetailRepository;

    public MemberDetailServiceImpl(MemberDetailRepository memberDetailRepository) {
        this.memberDetailRepository = memberDetailRepository;
    }


    @Override
    public MemberDetail registerMemberDetail(JoinRequest joinRequest) {


        MemberDetail memberDetail = MemberDetail.builder()
                .phoneNumber(joinRequest.getPhoneNumber())
                .nickName(joinRequest.getNickName())
                .build();

        memberDetailRepository.save(memberDetail);

        return memberDetail;
    }

    @Override
    public MemberDetail searchMemberDetailByMemberId(Long memberId) {
        return memberDetailRepository.findMemberDetailByMemberId(memberId);
    }

    @Override
    public MemberDetail modifyMemberDetailOnMember(Long memberId, MemberDetailModifyRequest memberDetailModifyRequest) {
        MemberDetail memberDetail = memberDetailRepository.findMemberDetailByMemberId(memberId);

        memberDetail.changeNickName(memberDetailModifyRequest.getNickName());
        memberDetail.changePhoneNumber(memberDetailModifyRequest.getPhoneNumber());

        return memberDetail;
    }

    @Override
    public void deleteDetailByMemberId(Long memberId) {
        memberDetailRepository.deleteMemberDetailByMemberId(memberId);
    }
}

