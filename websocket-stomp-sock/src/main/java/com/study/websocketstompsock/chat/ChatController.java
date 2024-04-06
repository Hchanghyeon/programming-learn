package com.study.websocketstompsock.chat;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.websocketstompsock.chat.dto.UserMessageRequest;

@RestController
@RequestMapping("/")
public class ChatController {

    @MessageMapping("/message/rooms/{roomId}")
    @SendTo("/receive/rooms/{roomId}")
    public ResponseEntity<String> sendMessage(
            @DestinationVariable String roomId,
            @RequestBody UserMessageRequest userMessageRequest
    ) {
        return ResponseEntity.ok("test");
    }
}
