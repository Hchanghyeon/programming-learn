package com.study.websocketstompsock.chat.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.websocketstompsock.auth.config.resolver.LoggedInMember;
import com.study.websocketstompsock.chat.domain.ChatRoom;
import com.study.websocketstompsock.chat.domain.ChatRoomMember;
import com.study.websocketstompsock.chat.domain.ChatRoomMessage;
import com.study.websocketstompsock.chat.domain.ChatRoomType;
import com.study.websocketstompsock.chat.dto.ChatRoomIdResponse;
import com.study.websocketstompsock.chat.dto.ChatRoomMessageResponse;
import com.study.websocketstompsock.chat.dto.ChatRoomResponse;
import com.study.websocketstompsock.chat.dto.GroupChatRoomCreateRequest;
import com.study.websocketstompsock.chat.repository.chatroom.ChatRoomRepository;
import com.study.websocketstompsock.chat.repository.chatroommember.ChatRoomMemberRepository;
import com.study.websocketstompsock.chat.repository.chatroommessage.ChatRoomMessageRepository;
import com.study.websocketstompsock.member.domain.Member;
import com.study.websocketstompsock.member.repository.MemberRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatRoomService {

    private static final String ENTERED_CHAT_ROOM_MESSAGE = "님이 방에 입장하셨습니다.";

    private final MemberRepository memberRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomMemberRepository chatRoomMemberRepository;
    private final ChatRoomMessageRepository chatRoomMessageRepository;

    @Transactional
    public ChatRoomIdResponse createGroupChatRoom(
            final LoggedInMember loggedInMember,
            final GroupChatRoomCreateRequest groupChatRoomCreateRequest
    ) {
        final ChatRoom chatRoom = groupChatRoomCreateRequest.toChatRoomEntity(ChatRoomType.GROUP,
                loggedInMember.memberId());
        final ChatRoom savedChatRoom = chatRoomRepository.save(chatRoom);

        createChatRoomMemberAndSave(loggedInMember, savedChatRoom.getId());
        createChatRoomMessageAndSave(loggedInMember, savedChatRoom.getId());

        return ChatRoomIdResponse.from(savedChatRoom.getId());
    }

    @Transactional
    public void enterChatRoom(final LoggedInMember loggedInMember, final Long chatRoomId) {
        final ChatRoom chatRoom = getChatRoomById(chatRoomId);

        checkChatRoomMemberExistsByMemberIdAndChatRoomId(loggedInMember, chatRoomId, chatRoom.getHostId());
        checkChatRoomMemberCountMax(chatRoom);

        chatRoom.increaseMemberCount();

        createChatRoomMemberAndSave(loggedInMember, chatRoomId);
        createChatRoomMessageAndSave(loggedInMember, chatRoomId);
    }

    public List<ChatRoomResponse> getChatRooms() {
        final List<ChatRoom> chatRooms = chatRoomRepository.findAll();

        return chatRooms.stream()
                .map(chatRoom -> {
                    final Member member = getMemberById(chatRoom.getHostId());
                    return ChatRoomResponse.from(chatRoom, member.getNickname());
                }).toList();
    }

    private Member getMemberById(final Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("해당 방의 방장이 존재하지 않습니다."));
    }

    private static void checkChatRoomMemberCountMax(final ChatRoom chatRoom) {
        if (chatRoom.isChatRoomMaxMember()) {
            throw new IllegalStateException("채팅방 인원이 꽉차서 들어갈 수 없습니다.");
        }
    }

    private void checkChatRoomMemberExistsByMemberIdAndChatRoomId(
            final LoggedInMember loggedInMember,
            final Long chatRoomId,
            final Long chatRoomHostId
    ) {
        chatRoomMemberRepository.findByMemberIdAndChatRoomId(loggedInMember.memberId(), chatRoomId)
                .ifPresent(chatRoomMember -> {
                    if (Objects.equals(chatRoomMember.getMemberId(), chatRoomHostId)) {
                        throw new IllegalStateException("채팅방 방장이므로 이미 채팅방에 참여하고있습니다.");
                    }

                    throw new IllegalStateException("이미 참여하고 있는 채팅방입니다.");
                });
    }

    private void createChatRoomMessageAndSave(final LoggedInMember loggedInMember, final Long savedChatRoom) {
        final ChatRoomMessage chatRoomMessage = createChatRoomMessage(
                loggedInMember,
                savedChatRoom,
                ENTERED_CHAT_ROOM_MESSAGE
        );
        chatRoomMessageRepository.save(chatRoomMessage);
    }

    private void createChatRoomMemberAndSave(final LoggedInMember loggedInMember, final Long savedChatRoom) {
        final ChatRoomMember chatRoomMember = createChatRoomMember(loggedInMember.memberId(), savedChatRoom);
        chatRoomMemberRepository.save(chatRoomMember);
    }

    private ChatRoom getChatRoomById(final Long chatRoomId) {
        return chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new EntityNotFoundException("채팅방을 찾을 수 없습니다."));
    }

    private ChatRoomMember createChatRoomMember(
            final Long memberId,
            final Long chatRoomId
    ) {
        return ChatRoomMember.builder()
                .memberId(memberId)
                .chatRoomId(chatRoomId)
                .build();
    }

    private ChatRoomMessage createChatRoomMessage(
            final LoggedInMember loggedInMember,
            final Long chatRoomId,
            final String message
    ) {
        return ChatRoomMessage.builder()
                .chatRoomId(chatRoomId)
                .senderId(loggedInMember.memberId())
                .message(loggedInMember.nickname() + message)
                .build();
    }

    public List<ChatRoomMessageResponse> getChatRoomMessages(final Long chatRoomId) {
        final List<ChatRoomMessage> chatRoomMessages = chatRoomMessageRepository.findByChatRoomId(chatRoomId);

        return chatRoomMessages.stream()
                .map(chatRoomMessage -> {
                    final Member member = getMemberById(chatRoomMessage.getSenderId());

                    return ChatRoomMessageResponse.of(chatRoomMessage, member.getNickname());
                })
                .toList();
    }
}
