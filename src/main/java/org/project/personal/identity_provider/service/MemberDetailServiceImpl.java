package org.project.personal.identity_provider.service;

import org.project.personal.identity_provider.dto.request.JoinRequest;
import org.project.personal.identity_provider.dto.request.MemberDetailModifyRequest;
import org.project.personal.identity_provider.entity.Member;
import org.project.personal.identity_provider.entity.MemberDetail;
import org.project.personal.identity_provider.repository.MemberDetailRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class MemberDetailServiceImpl implements MemberDetailService{

    private final MemberService memberService;
    private final MemberDetailRepository memberDetailRepository;

    public MemberDetailServiceImpl(MemberService memberService, MemberDetailRepository memberDetailRepository) {
        this.memberService = memberService;
        this.memberDetailRepository = memberDetailRepository;
    }


    @Override
    public MemberDetail registerMemberDetail(JoinRequest joinRequest) {

        Member member = memberService.registerMember(joinRequest);

        MemberDetail memberDetail = MemberDetail.builder()
                .memberId(member.getId())
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

