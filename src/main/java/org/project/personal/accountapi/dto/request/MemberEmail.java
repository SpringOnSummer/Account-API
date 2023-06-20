package org.project.personal.accountapi.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberEmail {
    private String local;
    private String domain;

    @Builder
    public MemberEmail(String local, String domain) {
        this.local = local;
        this.domain = domain;
    }
}
