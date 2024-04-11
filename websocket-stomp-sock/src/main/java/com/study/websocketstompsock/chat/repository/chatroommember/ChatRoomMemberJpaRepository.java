package com.study.websocketstompsock.chat.repository.chatroommember;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.websocketstompsock.chat.domain.ChatRoomMember;

@Repository

public interface ChatRoomMemberJpaRepository extends ChatRoomMemberRepository, JpaRepository<ChatRoomMember, Long> {

}
