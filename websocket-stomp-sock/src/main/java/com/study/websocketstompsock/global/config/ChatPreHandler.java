package com.study.websocketstompsock.global.config;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.study.websocketstompsock.auth.config.JwtProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatPreHandler implements ChannelInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private final JwtProvider jwtProvider;

    @Override
    public Message<?> preSend(final Message<?> message, final MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (StompCommand.CONNECT == accessor.getCommand()) { // websocket 연결요청
            final String accessToken = extractToken(accessor);
            log.info("CONNECT {}", accessToken);

            jwtProvider.verifyToken(accessToken);
        }
        return message;
    }

    private String extractToken(final StompHeaderAccessor accessor) {
        final String bearerToken = accessor.getFirstNativeHeader(AUTHORIZATION_HEADER);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        throw new IllegalStateException("토큰이 존재하지 않습니다.");
    }
}
