package com.study.websocketstompsock.chat.dto;

import java.time.LocalDateTime;

import com.study.websocketstompsock.chat.domain.ChatRoomMessage;

public record ChatMessageResponse(String message, LocalDateTime createdAt) {

    public static ChatMessageResponse from(final ChatRoomMessage savedChatRoomMessage) {
        return new ChatMessageResponse(savedChatRoomMessage.getMessage(), savedChatRoomMessage.getCreatedAt());
    }
}
