package org.project.personal.accountapi.adaptor;

import org.project.personal.accountapi.domain.MemberWithDetail;
import org.project.personal.accountapi.dto.request.JoinRequest;
import org.project.personal.accountapi.dto.response.MemberDataWithDetails;
import org.project.personal.accountapi.entity.Member;
import org.project.personal.accountapi.entity.MemberDetail;
import org.project.personal.accountapi.request.MemberPasswordModifyRequest;

public interface MemberAdaptor {
    Member registerMember(JoinRequest joinRequest);

    Member searchMemberByMemberId(Long id);

    Member modifyPassword(Long id, MemberPasswordModifyRequest memberModifyRequest);

    void deleteMemberById(Long id);

    Member searchMemberByEmail(String targetEmail);

    MemberDetail searchMemberDetailByEmail(String targetEmail);

    MemberDataWithDetails searchMemberDataByEmail(String memberEmail);

    MemberWithDetail searchMemberWithDetailByEmail(String targetEmail);
}
