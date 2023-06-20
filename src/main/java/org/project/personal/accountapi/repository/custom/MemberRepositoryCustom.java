package org.project.personal.accountapi.repository.custom;

import org.project.personal.accountapi.dto.response.MemberDataWithDetails;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MemberRepositoryCustom {
    MemberDataWithDetails findMemberInformationByEmail(String email);
}
