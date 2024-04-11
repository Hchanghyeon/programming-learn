package com.study.websocketstompsock.chat.domain;

import com.study.websocketstompsock.global.domain.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class ChatRoom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "chat_type", nullable = false)
    private ChatRoomType chatRoomType;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "current_member_count", nullable = false)
    private Integer currentMemberCount = 1;

    @Column(name = "max_member_count", nullable = false)
    private Integer maxMemberCount = 2;

    @Builder
    public ChatRoom(
            final ChatRoomType chatRoomType,
            final String title,
            final Integer maxMemberCount
    ) {
        this.chatRoomType = chatRoomType;
        this.title = title;
        this.maxMemberCount = maxMemberCount;
    }
}
