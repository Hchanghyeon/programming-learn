package com.study.websocketstompsock.chat.domain;

import com.study.websocketstompsock.global.domain.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoomMessage extends BaseEntity {

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
    private ChatRoomMessage(final String message, final Long senderId, final Long chatRoomId) {
        this.message = message;
        this.senderId = senderId;
        this.chatRoomId = chatRoomId;
    }
}
