package org.project.personal.accountapi.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class JoinRequest {

    private String email;
    private String memberName;
    private String password;

    private String phoneNumber;
    private String nickName;
}
