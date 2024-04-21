package com.pattern.designpattern.templatecallback;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeLogTemplate {

    public void execute(CallBack callBack){
        final long startTime = System.currentTimeMillis();
        callBack.call(); // 비즈니스 로직 실행
        final long endTime = System.currentTimeMillis();

        final long resultTime = startTime - endTime;
        log.info("resultTime = {}", resultTime);
    }
}
