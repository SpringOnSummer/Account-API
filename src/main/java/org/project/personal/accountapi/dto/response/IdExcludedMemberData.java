package org.project.personal.accountapi.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.project.personal.accountapi.entity.Member;

@Getter
@NoArgsConstructor
public class IdExcludedMemberData {

    private String name;
    private String emailLocal;
    private String emailDomain;
    private String password;

    @Builder
    public IdExcludedMemberData(String name, String emailLocal, String emailDomain, String password) {
        this.name = name;
        this.emailLocal = emailLocal;
        this.emailDomain = emailDomain;
        this.password = password;
    }

    public static IdExcludedMemberData fromMemberEntity(Member member){
        return IdExcludedMemberData.builder()
                .name(member.getName())
                .emailLocal(member.getEmailLocal())
                .emailDomain(member.getEmailDomain())
                .password(member.getPassword())
                .build();
    }
}
