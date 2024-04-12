package com.study.websocketstompsock.chat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import com.study.websocketstompsock.chat.dto.ChatMessageRequest;
import com.study.websocketstompsock.chat.dto.ChatMessageResponse;
import com.study.websocketstompsock.chat.service.ChatMessageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatMessageController {

    private final ChatMessageService chatMessageService;

    @MessageMapping("/rooms/{chatRoomId}")
    @SendTo("/subscribe/rooms/{chatRoomId}")
    public ResponseEntity<ChatMessageResponse> sendMessage(
            @DestinationVariable final Long chatRoomId,
            @Payload final ChatMessageRequest chatMessageRequest
    ) {

        return ResponseEntity.ok(
                chatMessageService.saveAndSendMessage(chatRoomId, chatMessageRequest));
    }
}
