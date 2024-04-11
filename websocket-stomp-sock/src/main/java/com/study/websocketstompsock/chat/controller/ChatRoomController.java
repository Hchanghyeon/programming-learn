package com.study.websocketstompsock.chat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.websocketstompsock.auth.config.resolver.LoggedInMember;
import com.study.websocketstompsock.auth.config.resolver.Login;
import com.study.websocketstompsock.chat.dto.ChatRoomIdResponse;
import com.study.websocketstompsock.chat.dto.GroupChatRoomCreateRequest;
import com.study.websocketstompsock.chat.service.ChatRoomService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat/rooms")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @PostMapping
    public ResponseEntity<ChatRoomIdResponse> createGroupChatRoom(
            @Login final LoggedInMember loggedInMember,
            @RequestBody final GroupChatRoomCreateRequest groupChatRoomCreateRequest
    ) {
        final ChatRoomIdResponse chatRoomIdResponse = chatRoomService.createGroupChatRoom(
                loggedInMember,
                groupChatRoomCreateRequest
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(chatRoomIdResponse);
    }
}
