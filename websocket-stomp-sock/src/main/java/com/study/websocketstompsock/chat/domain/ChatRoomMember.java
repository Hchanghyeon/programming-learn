package com.study.websocketstompsock.chat.domain;

import com.study.websocketstompsock.global.domain.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(
        name = "chat_room_member",
        uniqueConstraints = {@UniqueConstraint(name = "ChatRoomMember", columnNames = {"memberId", "chatRoomId"})}
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoomMember extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "chat_room_id", nullable = false)
    private Long chatRoomId;

    @Builder
    private ChatRoomMember(final Long memberId, final Long chatRoomId) {
        this.memberId = memberId;
        this.chatRoomId = chatRoomId;
    }
}
