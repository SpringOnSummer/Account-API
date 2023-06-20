package org.project.personal.accountapi.controller;

import org.project.personal.accountapi.dto.request.JoinRequest;
import org.project.personal.accountapi.entity.MemberDetail;
import org.project.personal.accountapi.request.MemberDetailModifyRequest;
import org.project.personal.accountapi.service.MemberDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberRestController {

    private final MemberDetailService memberDetailService;

    public MemberRestController(MemberDetailService memberDetailService) {
        this.memberDetailService = memberDetailService;
    }

    @PostMapping
    public ResponseEntity<Object> postMemberRequest(@RequestBody JoinRequest joinRequest){

        MemberDetail memberDetail = memberDetailService.registerMemberDetail(joinRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(memberDetail);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<Object> getMember(@PathVariable Long memberId){

        MemberDetail memberDetail = memberDetailService.searchMemberDetailByMemberId(memberId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(memberDetail);
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<Object> modifyMemberDetail(@PathVariable Long memberId, @RequestBody MemberDetailModifyRequest memberDetailModifyRequest){

        MemberDetail memberDetail = memberDetailService.modifyMemberDetailOnMember(memberId, memberDetailModifyRequest);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(memberDetail);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Object> deleteMemberDetail(@PathVariable Long memberId){

        memberDetailService.deleteDetailByMemberId(memberId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }
}
