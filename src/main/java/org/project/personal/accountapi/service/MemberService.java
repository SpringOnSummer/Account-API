package org.project.personal.accountapi.service;

import org.project.personal.accountapi.dto.request.JoinRequest;
import org.project.personal.accountapi.dto.response.MemberDataWithDetails;
import org.project.personal.accountapi.request.MemberDetailModifyRequest;
import org.project.personal.accountapi.request.MemberPasswordModifyRequest;

public interface MemberService {
    MemberDataWithDetails registerMember(JoinRequest joinRequest);

    MemberDataWithDetails searchMemberInformationByEmail(String memberEmail);

    void changePassword(MemberPasswordModifyRequest modifyRequest);

    void changeMemberDetail(MemberDetailModifyRequest modifyRequest);

    void deleteMemberByEmail(String targetEmail);
}
