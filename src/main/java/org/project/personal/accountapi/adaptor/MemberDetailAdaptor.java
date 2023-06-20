package org.project.personal.accountapi.adaptor;

import org.project.personal.accountapi.dto.request.JoinRequest;
import org.project.personal.accountapi.entity.Member;
import org.project.personal.accountapi.entity.MemberDetail;
import org.project.personal.accountapi.request.MemberDetailModifyRequest;

public interface MemberDetailAdaptor {

    MemberDetail registerMemberDetail(Member member, JoinRequest joinRequest);

    MemberDetail searchMemberDetailById(Long memberDetailId);

    MemberDetail modifyMemberDetailById(Long memberDetailId, MemberDetailModifyRequest memberDetailModifyRequest);

    void deleteMemberDetailById(Long memberDetailId);
}
