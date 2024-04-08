package com.study.websocketstompsock.member.dto;

import static com.study.websocketstompsock.member.domain.Role.*;

import com.study.websocketstompsock.member.domain.Member;

public record MemberCreateRequest(String nickname, String username, String email, String password) {

    public static Member toMemberEntity(final MemberCreateRequest memberCreateRequest, final String password) {
        return Member.builder()
                .nickname(memberCreateRequest.nickname())
                .username(memberCreateRequest.username())
                .email(memberCreateRequest.email())
                .password(password)
                .role(ROLE_USER)
                .build();
    }
}
