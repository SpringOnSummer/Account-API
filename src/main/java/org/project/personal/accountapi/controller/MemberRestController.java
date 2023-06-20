package org.project.personal.accountapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.project.personal.accountapi.dto.request.JoinRequest;
import org.project.personal.accountapi.dto.response.MemberDataWithDetails;
import org.project.personal.accountapi.request.MemberDetailModifyRequest;
import org.project.personal.accountapi.request.MemberPasswordModifyRequest;
import org.project.personal.accountapi.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/members")
public class MemberRestController {

    private final MemberService memberService;
    private final ObjectMapper objectMapper;

    public MemberRestController(MemberService memberService, ObjectMapper objectMapper) {
        this.memberService = memberService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public ResponseEntity<Object> register(@RequestBody JoinRequest joinRequest){

        MemberDataWithDetails memberDataWithDetails = memberService.registerMember(joinRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(memberDataWithDetails);
    }

    @GetMapping
    public ResponseEntity<Object> search(@RequestBody String targetEmail) throws JsonProcessingException {

        Map<String ,String > map = objectMapper.readValue(targetEmail, Map.class);

        MemberDataWithDetails memberDataWithDetails = memberService.searchMemberInformationByEmail(map.get("targetEmail"));

        return ResponseEntity.status(HttpStatus.OK)
                .body(memberDataWithDetails);
    }

    @PutMapping("/password")
    public ResponseEntity<Object> modifyPassword(@RequestBody MemberPasswordModifyRequest modifyRequest){

        memberService.changePassword(modifyRequest);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .build();
    }

    @PutMapping("/details")
    public ResponseEntity<Object> modifyDetail(@RequestBody MemberDetailModifyRequest modifyRequest) {

        memberService.changeMemberDetail(modifyRequest);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .build();
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody String targetEmail) throws JsonProcessingException {

        Map<String ,String > map = objectMapper.readValue(targetEmail, Map.class);

        memberService.deleteMemberByEmail(map.get("targetEmail"));

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }

}
