package org.project.personal.accountapi.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.project.personal.accountapi.entity.MemberDetail;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class IdExcludedMemberDetailData {

    private String phoneNumber;
    private String nickName;
    private LocalDateTime joinedAt;

    @Builder
    public IdExcludedMemberDetailData(String phoneNumber, String nickName, LocalDateTime joinedAt) {
        this.phoneNumber = phoneNumber;
        this.nickName = nickName;
        this.joinedAt = joinedAt;
    }

    public static IdExcludedMemberDetailData fromMemberDetailEntity(MemberDetail memberDetail) {
        return IdExcludedMemberDetailData.builder()
                .phoneNumber(memberDetail.getPhoneNumber())
                .nickName(memberDetail.getNickName())
                .joinedAt(memberDetail.getJoinedAt())
                .build();
    }
}
