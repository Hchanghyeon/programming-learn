package com.study.websocketstompsock.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.websocketstompsock.member.dto.MemberCreateRequest;
import com.study.websocketstompsock.member.dto.MemberIdResponse;
import com.study.websocketstompsock.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberIdResponse> registerMember(@RequestBody final MemberCreateRequest memberCreateRequest) {
        return ResponseEntity.ok(memberService.registerMember(memberCreateRequest));
    }
}
