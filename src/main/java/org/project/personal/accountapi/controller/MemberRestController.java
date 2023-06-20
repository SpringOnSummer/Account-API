package org.project.personal.accountapi.controller;

import org.project.personal.accountapi.adaptor.MemberDetailAdaptor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberRestController {

    private final MemberDetailAdaptor memberDetailService;

    public MemberRestController(MemberDetailAdaptor memberDetailService) {
        this.memberDetailService = memberDetailService;
    }

}
