package com.study.websocketstompsock.member.dto;

import com.study.websocketstompsock.member.domain.Member;
import com.study.websocketstompsock.member.domain.Role;

public record MemberCreateRequest(String email, String password) {

    public static Member toMemberEntity(final MemberCreateRequest memberCreateRequest, final String password) {
        return Member.builder()
                .email(memberCreateRequest.email())
                .password(password)
                .role(Role.USER)
                .build();
    }
}
