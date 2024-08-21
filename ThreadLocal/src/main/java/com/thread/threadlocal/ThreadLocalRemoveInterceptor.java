package com.thread.threadlocal;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ThreadLocalRemoveInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final Exception ex) throws Exception {
        ThreadLocalComponent.userNameThreadLocal.remove();
    }
}
