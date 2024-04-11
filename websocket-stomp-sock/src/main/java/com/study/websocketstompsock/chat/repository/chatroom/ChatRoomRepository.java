package com.study.websocketstompsock.chat.repository.chatroom;

import com.study.websocketstompsock.chat.domain.ChatRoom;

public interface ChatRoomRepository {

    ChatRoom save(final ChatRoom chatRoom);
}
