package org.project.personal.accountapi.service;

import org.project.personal.accountapi.dto.request.JoinRequest;
import org.project.personal.accountapi.entity.Member;
import org.project.personal.accountapi.request.MemberPasswordModifyRequest;

public interface MemberService {
    Member registerMember(JoinRequest joinRequest);

    Member searchMemberByMemberId(Long id);

    Member modifyPassword(Long id, MemberPasswordModifyRequest memberModifyRequest);

    void deleteMember(Long id);
}
