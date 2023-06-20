package org.project.personal.accountapi.domain;

import lombok.Getter;
import org.project.personal.accountapi.entity.Member;
import org.project.personal.accountapi.entity.MemberDetail;

@Getter
public class MemberWithDetail {

    Member member;
    MemberDetail memberDetail;

    public MemberWithDetail(Member member, MemberDetail memberDetail) {
        this.member = member;
        this.memberDetail = memberDetail;
    }
}
