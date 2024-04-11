package com.study.websocketstompsock.chat.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ChatRoomType {
    PERSONAL("개인"),
    GROUP("그룹"),
    ;

    private final String name;
}
