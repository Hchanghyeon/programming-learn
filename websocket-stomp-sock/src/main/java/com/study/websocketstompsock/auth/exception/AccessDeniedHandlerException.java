package com.study.websocketstompsock.auth.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.websocketstompsock.global.exception.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AccessDeniedHandlerException implements AccessDeniedHandler {

    @Override
    public void handle(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final AccessDeniedException accessDeniedException
    ) throws IOException {
        log.error(accessDeniedException.getMessage());

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        final ResponseEntity<ErrorResponse> responseEntity = ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ErrorResponse("접근 권한이 없습니다."));

        response.getWriter().write(new ObjectMapper().writeValueAsString(responseEntity));

    }
}
