package org.project.personal.accountapi.request;

import lombok.Getter;

@Getter
public class MemberPasswordModifyRequest {

    String currentPassword;
    String resetPassword;
}
