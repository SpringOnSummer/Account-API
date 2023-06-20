package org.project.personal.accountapi.utils;

import org.project.personal.accountapi.dto.request.JoinRequest;
import org.project.personal.accountapi.dto.request.MemberEmail;
import org.project.personal.accountapi.entity.Member;
import org.project.personal.accountapi.entity.MemberDetail;
import org.project.personal.accountapi.request.MemberDetailModifyRequest;
import org.project.personal.accountapi.request.MemberPasswordModifyRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;

public class MemberTestUtils {

    public static JoinRequest getJoinRequest(){
        JoinRequest joinRequest = new JoinRequest();
        /**
         *     private String email;
         *     private String memberName;
         *     private String password;
         *     private String phoneNumber;
         *     private String nickName;
         *
         *     {
         *     "email" : "test@email.com",
         *     "memberName" : "test_member",
         *     "password" : "password",
         *     "phoneNumber" : "010-1234-5678",
         *     "nickName" : "nickName"
         *      }
         */

        ReflectionTestUtils.setField(joinRequest, "email", "test@email.com");
        ReflectionTestUtils.setField(joinRequest, "memberName","test_member");
        ReflectionTestUtils.setField(joinRequest, "password", "password");
        ReflectionTestUtils.setField(joinRequest, "phoneNumber","010-1234-5678");
        ReflectionTestUtils.setField(joinRequest, "nickName","nickName");

        return joinRequest;
    }

    public static MemberPasswordModifyRequest getMemberPasswordModifyRequest(){
        MemberPasswordModifyRequest request = new MemberPasswordModifyRequest();

        ReflectionTestUtils.setField(request, "currentPassword", "currentPassword");
        ReflectionTestUtils.setField(request, "resetPassword", "resetPassword");

        return request;
    }

    public static MemberDetailModifyRequest getMemberDetailModifyRequest(){
        MemberDetailModifyRequest memberDetailModifyRequest = new MemberDetailModifyRequest();

        ReflectionTestUtils.setField(memberDetailModifyRequest, "phoneNumber","010-9876-5432");
        ReflectionTestUtils.setField(memberDetailModifyRequest, "nickName","changed Nick Name");

        return memberDetailModifyRequest;
    }

    public static Member getMember(){
        JoinRequest joinRequest = getJoinRequest();

        MemberEmail email = MembersEmailUtils.convertEmail(joinRequest.getEmail());

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Member member = Member.builder()
                .memberName(joinRequest.getMemberName())
                .emailLocal(email.getLocal())
                .emailDomain(email.getDomain())
                .password(passwordEncoder.encode(joinRequest.getPassword()))
                .build();

        return member;
    }

    public static MemberDetail getMemberDetail(){
        JoinRequest joinRequest = getJoinRequest();

        Member member = getMember();

        MemberDetail memberDetail = MemberDetail.builder()
                .memberId(member.getId())
                .nickName(joinRequest.getNickName())
                .phoneNumber(joinRequest.getPhoneNumber())
                .joinedAt(LocalDateTime.now())
                .build();

        ReflectionTestUtils.setField(memberDetail, "member", member);

        return memberDetail;

    }

}
