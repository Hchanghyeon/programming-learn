package com.study.websocketstompsock.auth.dto;

public record AccessTokenResponse(String value) {

    public static AccessTokenResponse from(final String accessToken) {
        return new AccessTokenResponse(accessToken);
    }
}
