package com.study.websocketstompsock.chat.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.websocketstompsock.chat.domain.ChatRoom;
import com.study.websocketstompsock.chat.domain.ChatRoomMessage;
import com.study.websocketstompsock.chat.dto.ChatMessageRequest;
import com.study.websocketstompsock.chat.dto.ChatMessageResponse;
import com.study.websocketstompsock.chat.repository.chatroom.ChatRoomRepository;
import com.study.websocketstompsock.chat.repository.chatroommember.ChatRoomMemberRepository;
import com.study.websocketstompsock.chat.repository.chatroommessage.ChatRoomMessageRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatMessageService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomMemberRepository chatRoomMemberRepository;
    private final ChatRoomMessageRepository chatRoomMessageRepository;

    @Transactional
    public ChatMessageResponse saveAndSendMessage(
            final Long chatRoomId,
            final ChatMessageRequest chatMessageRequest
    ) {
        final ChatRoom chatRoom = getChatRoomById(chatRoomId);
        checkChatRoomMemberExistsByMemberIdAndChatRoomId(chatMessageRequest.senderId(), chatRoomId);

        final ChatRoomMessage chatRoomMessage = chatMessageRequest.toEntity(chatRoomId, chatMessageRequest.senderId());
        final ChatRoomMessage savedChatRoomMessage = chatRoomMessageRepository.save(chatRoomMessage);

        return ChatMessageResponse.from(savedChatRoomMessage);
    }

    private void checkChatRoomMemberExistsByMemberIdAndChatRoomId(final Long memberId, final Long chatRoomId) {
        chatRoomMemberRepository.findByMemberIdAndChatRoomId(memberId, chatRoomId)
                .orElseThrow(() -> new EntityNotFoundException("해당 채팅방에 속하지 않은 사용자입니다."));
    }

    private ChatRoom getChatRoomById(final Long chatRoomId) {
        return chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new EntityNotFoundException("찾는 채팅방이 없습니다."));
    }
}
