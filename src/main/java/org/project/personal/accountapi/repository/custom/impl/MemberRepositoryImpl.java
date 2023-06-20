package org.project.personal.accountapi.repository.custom.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.project.personal.accountapi.domain.MemberWithDetail;
import org.project.personal.accountapi.dto.request.MemberEmail;
import org.project.personal.accountapi.dto.response.MemberDataWithDetails;
import org.project.personal.accountapi.entity.Member;
import org.project.personal.accountapi.entity.MemberDetail;
import org.project.personal.accountapi.entity.QMember;
import org.project.personal.accountapi.entity.QMemberDetail;
import org.project.personal.accountapi.repository.custom.MemberRepositoryCustom;
import org.project.personal.accountapi.utils.MembersEmailUtils;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.Optional;

public class MemberRepositoryImpl extends QuerydslRepositorySupport implements MemberRepositoryCustom {

    public MemberRepositoryImpl() {
        super(Member.class);
    }

    @Override
    public Optional<MemberDataWithDetails> findMemberInformationByEmail(String email) {

        QMember member = QMember.member;
        QMemberDetail memberDetail = QMemberDetail.memberDetail;

        return Optional.ofNullable(
                memberInnerJoinDetailWhereEmailEqual(email)
                        .select(Projections.constructor(MemberDataWithDetails.class, member, memberDetail))
                        .fetchOne()
        );
    }

    @Override
    public Optional<Member> findMemberEntityByMemberEmail(String targetEmail) {
        return Optional.ofNullable(
                memberInnerJoinDetailWhereEmailEqual(targetEmail).fetchOne()
        );
    }

    @Override
    public Optional<MemberDetail> findMemberDetailEntityByMemberEmail(String targetEmail) {
        QMemberDetail memberDetail = QMemberDetail.memberDetail;

        return Optional.ofNullable(
                memberInnerJoinDetailWhereEmailEqual(targetEmail)
                        .select(memberDetail).fetchOne()
        );
    }

    @Override
    public Optional<MemberWithDetail> findMemberWithDetailByEmail(String targetEmail) {
        
        QMember member = QMember.member;
        QMemberDetail memberDetail = QMemberDetail.memberDetail;
        
        return Optional.ofNullable(memberInnerJoinDetailWhereEmailEqual(targetEmail)
                .select(Projections.constructor(MemberWithDetail.class, member, memberDetail))
                .fetchOne());
    }

    private JPQLQuery<Member> memberInnerJoinDetailWhereEmailEqual(String email) {

        QMember member = QMember.member;
        QMemberDetail memberDetail = QMemberDetail.memberDetail;

        MemberEmail memberEmail = MembersEmailUtils.convertEmail(email);

        return from(member)
                .innerJoin(memberDetail).on(member.id.eq(memberDetail.memberId)).fetchJoin()
                .where(member.emailLocal.eq(memberEmail.getLocal()).and(member.emailDomain.eq(memberEmail.getDomain())));

    }

}
