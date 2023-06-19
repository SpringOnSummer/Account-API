package org.project.personal.identity_provider.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * CREATE TABLE member_details(
 *     member_detail_id BIGINT AUTO_INCREMENT,
 *     member_id BIGINT NOT NULL ,
 *     phone_number VARCHAR(100) NOT NULL ,
 *     nick_name VARCHAR(100) NOT NULL ,
 *     joined_at DATETIME NOT NULL,
 *     CONSTRAINT
 *         PRIMARY KEY (member_detail_id),
 *         FOREIGN KEY (member_id) REFERENCES members(member_id)
 * );
 */
@Entity
@Table(name = "member_details")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Getter
public class MemberDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_detail_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    private Member member;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "nick_name")
    private String nickName;

    @CreatedDate
    @Column(name = "joined_at")
    private LocalDateTime joinedAt;

    @Builder
    public MemberDetail(Long memberId, String phoneNumber, String nickName, LocalDateTime joinedAt) {
        this.memberId = memberId;
        this.phoneNumber = phoneNumber;
        this.nickName = nickName;
        this.joinedAt = joinedAt;
    }

    public void changeNickName(String nickName){
        this.nickName = nickName;
    }

    public void changePhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
}
