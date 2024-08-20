package com.document.swagger;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MemberType {
    VIP,
    BLACK,
    SILVER,
    ;

    private static final String PREFIX = "member";

    @JsonValue
    public String getValue(){
        return PREFIX + this.name();
    }
}
