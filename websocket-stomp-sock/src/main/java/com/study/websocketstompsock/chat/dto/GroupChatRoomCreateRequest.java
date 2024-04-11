package com.study.websocketstompsock.chat.dto;

import com.study.websocketstompsock.chat.domain.ChatRoom;
import com.study.websocketstompsock.chat.domain.ChatRoomType;

public record GroupChatRoomCreateRequest(String title, Integer maxMemberCount) {

    public ChatRoom toChatRoomEntity(final ChatRoomType chatRoomType) {
        return ChatRoom.builder()
                .chatRoomType(chatRoomType)
                .title(title)
                .maxMemberCount(maxMemberCount)
                .build();
    }
}
