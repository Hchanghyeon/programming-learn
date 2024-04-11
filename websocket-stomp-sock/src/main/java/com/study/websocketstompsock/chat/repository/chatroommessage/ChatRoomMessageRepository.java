package com.study.websocketstompsock.chat.repository.chatroommessage;

import com.study.websocketstompsock.chat.domain.ChatRoomMessage;

public interface ChatRoomMessageRepository {

    ChatRoomMessage save(final ChatRoomMessage chatRoomMessage);
}
