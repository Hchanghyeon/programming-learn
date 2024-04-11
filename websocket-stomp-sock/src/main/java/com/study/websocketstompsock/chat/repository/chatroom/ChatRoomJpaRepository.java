package com.study.websocketstompsock.chat.repository.chatroom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.websocketstompsock.chat.domain.ChatRoom;

@Repository
public interface ChatRoomJpaRepository extends ChatRoomRepository, JpaRepository<ChatRoom, Long> {

}
