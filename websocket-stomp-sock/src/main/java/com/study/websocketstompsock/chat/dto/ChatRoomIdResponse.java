package com.study.websocketstompsock.chat.dto;

public record ChatRoomIdResponse(Long id) {

    public static ChatRoomIdResponse from(final Long chatRoomId) {
        return new ChatRoomIdResponse(chatRoomId);
    }
}
