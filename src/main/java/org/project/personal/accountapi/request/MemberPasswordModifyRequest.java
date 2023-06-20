package org.project.personal.accountapi.request;

import lombok.Getter;

@Getter
public class MemberPasswordModifyRequest {

    String targetEmail;
    String currentPassword;
    String resetPassword;
}
