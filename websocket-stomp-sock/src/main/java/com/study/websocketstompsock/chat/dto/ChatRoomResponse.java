package com.study.websocketstompsock.chat.dto;

import java.time.LocalDateTime;

import com.study.websocketstompsock.chat.domain.ChatRoom;

public record ChatRoomResponse(
        Long chatRoomId,
        String title,
        Integer currentMemberCount,
        Integer maxMemberCount,
        String hostNickname,
        LocalDateTime createdAt
) {

    public static ChatRoomResponse from(final ChatRoom chatRoom, final String hostNickname) {
        return new ChatRoomResponse(
                chatRoom.getId(),
                chatRoom.getTitle(),
                chatRoom.getCurrentMemberCount(),
                chatRoom.getMaxMemberCount(),
                hostNickname,
                chatRoom.getCreatedAt()
        );
    }
}
