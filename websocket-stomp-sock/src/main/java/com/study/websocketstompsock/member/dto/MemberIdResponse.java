package com.study.websocketstompsock.member.dto;

public record MemberIdResponse(Long memberId) {

    public static MemberIdResponse from(final Long memberId) {
        return new MemberIdResponse(memberId);
    }
}
