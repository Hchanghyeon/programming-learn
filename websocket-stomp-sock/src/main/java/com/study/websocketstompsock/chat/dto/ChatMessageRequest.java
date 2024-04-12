package com.study.websocketstompsock.chat.dto;

import com.study.websocketstompsock.chat.domain.ChatRoomMessage;

public record ChatMessageRequest(String message, Long senderId) {

    public ChatRoomMessage toEntity(final Long chatRoomId, final Long senderId) {
        return ChatRoomMessage.builder()
                .chatRoomId(chatRoomId)
                .message(message)
                .senderId(senderId)
                .build();
    }
}
