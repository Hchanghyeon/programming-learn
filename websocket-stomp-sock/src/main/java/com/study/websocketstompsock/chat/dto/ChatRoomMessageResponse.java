package com.study.websocketstompsock.chat.dto;

import com.study.websocketstompsock.chat.domain.ChatRoomMessage;

public record ChatRoomMessageResponse(Long chatRoomId, String message, Long senderId, String senderNickname) {

    public static ChatRoomMessageResponse of(final ChatRoomMessage chatRoomMessage, final String senderNickname) {
        return new ChatRoomMessageResponse(
                chatRoomMessage.getChatRoomId(),
                chatRoomMessage.getMessage(),
                chatRoomMessage.getSenderId(),
                senderNickname
        );
    }
}
