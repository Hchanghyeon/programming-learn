package com.study.websocketstompsock.auth.dto;

public record AuthenticatedMember(String nickname, String accessToken) {

    public static AuthenticatedMember from(final String nickname, final String accessToken) {
        return new AuthenticatedMember(nickname, accessToken);
    }
}
