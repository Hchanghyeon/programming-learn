package com.study.websocket;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private static final Map<String, WebSocketSession> webSocketSessionMap = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(final WebSocketSession session) {
        log.info(session.getId() + "번이 연결되었습니다.");
        webSocketSessionMap.put(session.getId(), session);
    }

    @Override
    protected void handleTextMessage(final WebSocketSession session, final TextMessage message) {
        for (Map.Entry<String, WebSocketSession> webSocketSessionEntry : webSocketSessionMap.entrySet()) {
            if (!webSocketSessionEntry.getKey().equals(session.getId())) {
                final TextMessage textMessage = new TextMessage(session.getId() + "님:" + message.getPayload());

                try {
                    webSocketSessionEntry.getValue().sendMessage(textMessage);
                } catch (IOException e) {
                    log.error("메시지 전송 불가");
                }
            }
        }
    }

    @Override
    public void afterConnectionClosed(final WebSocketSession session, final CloseStatus status) {
        log.info(session.getId() + "번의 연결이 끊어졌습니다.");
        webSocketSessionMap.remove(session.getId());
    }
}
