package org.project.personal.accountapi.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 *
 CREATE TABLE members(
 member_id BIGINT AUTO_INCREMENT,
 member_name VARCHAR(100) NOT NULL ,
 email_local VARCHAR(100) NOT NULL ,
 email_domain VARCHAR(100) NOT NULL ,
 password VARCHAR(100) NOT NULL ,
 CONSTRAINT
 PRIMARY KEY (member_id)
 );
 */

@Entity
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "email_local")
    private String emailLocal;

    @Column(name = "email_domain")
    private String emailDomain;

    @Column(name = "password")
    private String password;

    @Builder
    public Member(String memberName, String emailLocal, String emailDomain, String password) {
        this.memberName = memberName;
        this.emailLocal = emailLocal;
        this.emailDomain = emailDomain;
        this.password = password;
    }

    public void modifyName(String memberName){
        this.memberName = memberName;
    }

    public void changePassword(String password){
        this.password = password;
    }


}


