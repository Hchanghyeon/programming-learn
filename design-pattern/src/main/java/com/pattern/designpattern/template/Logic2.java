package com.pattern.designpattern.template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Logic2 extends AbstractTemplate{

    @Override
    protected void call() {
        log.info("비즈니스 로직2 실행");
    }
}
