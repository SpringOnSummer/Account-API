package org.project.personal.identity_provider.service;

import org.project.personal.identity_provider.dto.request.MemberDetailModifyRequest;
import org.project.personal.identity_provider.dto.request.JoinRequest;
import org.project.personal.identity_provider.entity.MemberDetail;

public interface MemberDetailService {


    MemberDetail registerMemberDetail(JoinRequest joinRequest);

    MemberDetail searchMemberDetailByMemberId(Long memberId);

    MemberDetail modifyMemberDetailOnMember(Long memberId, MemberDetailModifyRequest memberDetailModifyRequest);

    void deleteDetailByMemberId(Long memberId);

}
