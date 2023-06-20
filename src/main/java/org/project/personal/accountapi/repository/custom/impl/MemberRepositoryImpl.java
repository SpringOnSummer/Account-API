package org.project.personal.accountapi.repository.custom.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.project.personal.accountapi.dto.request.MemberEmail;
import org.project.personal.accountapi.dto.response.MemberDataWithDetails;
import org.project.personal.accountapi.entity.Member;
import org.project.personal.accountapi.entity.QMember;
import org.project.personal.accountapi.entity.QMemberDetail;
import org.project.personal.accountapi.repository.custom.MemberRepositoryCustom;
import org.project.personal.accountapi.utils.MembersEmailUtils;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class MemberRepositoryImpl extends QuerydslRepositorySupport implements MemberRepositoryCustom {

    public MemberRepositoryImpl() {
        super(Member.class);
    }

    @Override
    public MemberDataWithDetails findMemberInformationByEmail(String email){

        QMember member = QMember.member;
        QMemberDetail memberDetail = QMemberDetail.memberDetail;

        return memberJPQLQueryByEmail(email)
                .select(Projections.constructor(MemberDataWithDetails.class, member, memberDetail))
                .fetchOne();
    }

    private JPQLQuery<Member> memberJPQLQueryByEmail(String email){

        QMember member = QMember.member;
        QMemberDetail memberDetail = QMemberDetail.memberDetail;

        MemberEmail memberEmail = MembersEmailUtils.convertEmail(email);

        return from(member)
                .innerJoin(member, memberDetail.member).fetchJoin()
                .where(member.emailLocal.eq(memberEmail.getLocal()).and(member.emailDomain.eq(memberEmail.getDomain())));

    }
}
