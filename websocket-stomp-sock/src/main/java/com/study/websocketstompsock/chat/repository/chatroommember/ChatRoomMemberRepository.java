package com.study.websocketstompsock.chat.repository.chatroommember;

import com.study.websocketstompsock.chat.domain.ChatRoomMember;

public interface ChatRoomMemberRepository {

    ChatRoomMember save(final ChatRoomMember chatRoomMember);
}
