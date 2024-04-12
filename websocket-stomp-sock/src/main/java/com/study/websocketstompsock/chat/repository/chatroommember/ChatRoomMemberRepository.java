package com.study.websocketstompsock.chat.repository.chatroommember;

import java.util.Optional;

import com.study.websocketstompsock.chat.domain.ChatRoomMember;

public interface ChatRoomMemberRepository {

    ChatRoomMember save(final ChatRoomMember chatRoomMember);

    Optional<ChatRoomMember> findByMemberIdAndChatRoomId(Long aLong, Long chatRoomId);
}
