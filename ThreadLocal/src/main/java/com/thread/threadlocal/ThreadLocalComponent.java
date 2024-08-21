package com.thread.threadlocal;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ThreadLocalComponent {
    
    public static final ThreadLocal<String> userNameThreadLocal = new ThreadLocal<>();

    public void checkThreadLocal(){
        log.info("check: {}", Thread.currentThread().getName());
        log.info(userNameThreadLocal.get());
    }

    public void addUserName(String userName){
        log.info("add: {}", Thread.currentThread().getName());
        userNameThreadLocal.set(userName);
    }
}
