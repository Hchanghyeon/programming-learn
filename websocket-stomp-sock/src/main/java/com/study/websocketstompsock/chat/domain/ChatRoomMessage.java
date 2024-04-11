package com.study.websocketstompsock.chat.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoomMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "sender_id", nullable = false)
    private Long senderId;

    @Column(name = "chat_room_id", nullable = false)
    private Long chatRoomId;

    @Builder
    public ChatRoomMessage(final String message, final Long senderId, final Long chatRoomId) {
        this.message = message;
        this.senderId = senderId;
        this.chatRoomId = chatRoomId;
    }
}
