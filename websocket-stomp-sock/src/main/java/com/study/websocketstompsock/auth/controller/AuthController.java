package com.study.websocketstompsock.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.websocketstompsock.auth.dto.AuthenticatedMember;
import com.study.websocketstompsock.auth.dto.MemberLoginRequest;
import com.study.websocketstompsock.auth.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticatedMember> memberLogin(@RequestBody final MemberLoginRequest memberLoginRequest) {
        final AuthenticatedMember authenticatedMember = authService.memberLogin(memberLoginRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authenticatedMember);
    }
}
