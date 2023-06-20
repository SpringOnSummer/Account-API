package org.project.personal.accountapi.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.project.personal.accountapi.entity.Member;
import org.project.personal.accountapi.entity.MemberDetail;

@Getter
@NoArgsConstructor
public class MemberDataWithDetails {

    IdExcludedMemberData member;
    IdExcludedMemberDetailData memberDetail;

    @Builder
    public MemberDataWithDetails(Member member, MemberDetail memberDetail) {
        this.member = IdExcludedMemberData.fromMemberEntity(member);
        this.memberDetail = IdExcludedMemberDetailData.fromMemberDetailEntity(memberDetail);
    }
}
