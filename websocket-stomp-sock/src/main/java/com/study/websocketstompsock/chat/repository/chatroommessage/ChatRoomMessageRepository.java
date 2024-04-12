package com.study.websocketstompsock.chat.repository.chatroommessage;

import java.util.List;

import com.study.websocketstompsock.chat.domain.ChatRoomMessage;

public interface ChatRoomMessageRepository {

    ChatRoomMessage save(final ChatRoomMessage chatRoomMessage);

    List<ChatRoomMessage> findByChatRoomId(final Long chatRoomId);
}
