package org.project.personal.accountapi.repository.custom;

import org.project.personal.accountapi.domain.MemberWithDetail;
import org.project.personal.accountapi.dto.response.MemberDataWithDetails;
import org.project.personal.accountapi.entity.Member;
import org.project.personal.accountapi.entity.MemberDetail;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface MemberRepositoryCustom {
    Optional<MemberDataWithDetails> findMemberInformationByEmail(String email);

    Optional<Member> findMemberEntityByMemberEmail(String targetEmail);

    Optional<MemberDetail> findMemberDetailEntityByMemberEmail(String targetEmail);

    Optional<MemberWithDetail> findMemberWithDetailByEmail(String targetEmail);
}
