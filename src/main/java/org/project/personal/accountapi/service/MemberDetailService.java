package org.project.personal.accountapi.service;

import org.project.personal.accountapi.dto.request.JoinRequest;
import org.project.personal.accountapi.entity.MemberDetail;
import org.project.personal.accountapi.request.MemberDetailModifyRequest;

public interface MemberDetailService {


    MemberDetail registerMemberDetail(JoinRequest joinRequest);

    MemberDetail searchMemberDetailByMemberId(Long memberId);

    MemberDetail modifyMemberDetailOnMember(Long memberId, MemberDetailModifyRequest memberDetailModifyRequest);

    void deleteDetailByMemberId(Long memberId);

}
