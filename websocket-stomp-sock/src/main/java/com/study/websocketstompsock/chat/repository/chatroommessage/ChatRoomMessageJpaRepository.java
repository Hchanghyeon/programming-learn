package com.study.websocketstompsock.chat.repository.chatroommessage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.websocketstompsock.chat.domain.ChatRoomMessage;

@Repository
public interface ChatRoomMessageJpaRepository extends ChatRoomMessageRepository, JpaRepository<ChatRoomMessage, Long> {

}
