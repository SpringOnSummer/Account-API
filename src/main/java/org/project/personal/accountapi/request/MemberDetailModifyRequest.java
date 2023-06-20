package org.project.personal.accountapi.request;

import lombok.Getter;

@Getter
public class MemberDetailModifyRequest {

    private String targetEmail;
    private String phoneNumber;
    private String nickName;
}
