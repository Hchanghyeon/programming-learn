package com.pattern.designpattern.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Logic2 implements Strategy{

    @Override
    public void call() {
        log.info("비즈니스 로직2 실행");
    }
}
