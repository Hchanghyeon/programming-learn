package com.study.websocketstompsock;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class WebSocketController {

    @MessageMapping("/send/message/{roomId}")
    @SendTo("/sub/room/{roomId}")
    public ResponseEntity<String> sendMessage(
            @DestinationVariable String roomId,
            @RequestBody UserMessageRequest userMessageRequest
    ) {
        System.out.println(userMessageRequest.toString());

        return ResponseEntity.ok("test");
    }
}
