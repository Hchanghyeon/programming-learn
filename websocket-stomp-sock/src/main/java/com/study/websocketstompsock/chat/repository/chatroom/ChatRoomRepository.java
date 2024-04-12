package com.study.websocketstompsock.chat.repository.chatroom;

import java.util.List;
import java.util.Optional;

import com.study.websocketstompsock.chat.domain.ChatRoom;

public interface ChatRoomRepository {

    ChatRoom save(final ChatRoom chatRoom);

    Optional<ChatRoom> findById(Long chatRoomId);

    List<ChatRoom> findAll();
}
