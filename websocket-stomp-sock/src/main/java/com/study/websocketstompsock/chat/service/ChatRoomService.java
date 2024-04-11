package com.study.websocketstompsock.chat.service;

import org.springframework.stereotype.Service;

import com.study.websocketstompsock.auth.config.resolver.LoggedInMember;
import com.study.websocketstompsock.chat.domain.ChatRoom;
import com.study.websocketstompsock.chat.domain.ChatRoomMember;
import com.study.websocketstompsock.chat.domain.ChatRoomMessage;
import com.study.websocketstompsock.chat.domain.ChatRoomType;
import com.study.websocketstompsock.chat.dto.ChatRoomIdResponse;
import com.study.websocketstompsock.chat.dto.GroupChatRoomCreateRequest;
import com.study.websocketstompsock.chat.repository.chatroom.ChatRoomRepository;
import com.study.websocketstompsock.chat.repository.chatroommember.ChatRoomMemberRepository;
import com.study.websocketstompsock.chat.repository.chatroommessage.ChatRoomMessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private static final String ENTERED_CHAT_ROOM_MESSAGE = "님이 방에 입장하셨습니다.";

    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomMemberRepository chatRoomMemberRepository;
    private final ChatRoomMessageRepository chatRoomMessageRepository;

    public ChatRoomIdResponse createGroupChatRoom(
            final LoggedInMember loggedInMember,
            final GroupChatRoomCreateRequest groupChatRoomCreateRequest
    ) {
        final ChatRoom chatRoom = groupChatRoomCreateRequest.toChatRoomEntity(ChatRoomType.GROUP);
        final ChatRoom savedChatRoom = chatRoomRepository.save(chatRoom);

        final ChatRoomMember chatRoomMember = createChatRoomMember(loggedInMember, savedChatRoom);
        chatRoomMemberRepository.save(chatRoomMember);

        final ChatRoomMessage chatRoomMessage = createChatRoomMessage(loggedInMember, savedChatRoom);
        chatRoomMessageRepository.save(chatRoomMessage);

        return ChatRoomIdResponse.from(savedChatRoom.getId());
    }

    private ChatRoomMember createChatRoomMember(
            final LoggedInMember loggedInMember,
            final ChatRoom savedChatRoom
    ) {
        return ChatRoomMember.builder()
                .memberId(loggedInMember.memberId())
                .chatRoomId(savedChatRoom.getId())
                .build();
    }

    private ChatRoomMessage createChatRoomMessage(
            final LoggedInMember loggedInMember,
            final ChatRoom savedChatRoom
    ) {
        return ChatRoomMessage.builder()
                .chatRoomId(savedChatRoom.getId())
                .senderId(loggedInMember.memberId())
                .message(loggedInMember.nickname() + ENTERED_CHAT_ROOM_MESSAGE)
                .build();
    }
}
