package com.study.async.asyncannotationtest;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AsyncProcess {

    @Async
    public void init() throws InterruptedException {
        log.info(Thread.currentThread().getName() + ": 시작");
        Thread.sleep(3000);
        log.info(Thread.currentThread().getName() + ": 종료");
    }
}
