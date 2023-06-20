package org.project.personal.accountapi.service;

import org.project.personal.accountapi.dto.request.JoinRequest;
import org.project.personal.accountapi.dto.request.MemberEmail;
import org.project.personal.accountapi.entity.Member;
import org.project.personal.accountapi.repository.MemberRepository;
import org.project.personal.accountapi.request.MemberPasswordModifyRequest;
import org.project.personal.accountapi.utils.MembersEmailUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
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
    public Member searchMemberByMemberId(Long id) {
        return memberRepository.findById(id).orElseThrow();
    }

    @Override
    public Member modifyPassword(Long id, MemberPasswordModifyRequest memberModifyRequest) {

        Member member = memberRepository.findById(id).orElseThrow();

        if (memberModifyRequest.getCurrentPassword().matches(member.getPassword())) {
            member.changePassword(memberModifyRequest.getResetPassword());
        }

        return member;
    }

    @Override
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
