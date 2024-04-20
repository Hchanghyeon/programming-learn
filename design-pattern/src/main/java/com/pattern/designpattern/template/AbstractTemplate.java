package com.pattern.designpattern.template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {

    public void execute(){
        final long startTime = System.currentTimeMillis();
        call(); // 비즈니스 로직 실행
        final long endTime = System.currentTimeMillis();

        final long resultTime = startTime - endTime;
        log.info("resultTime = {}", resultTime);
    }

    protected abstract void call();
}
