package com.pattern.designpattern.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV2 {

    public void execute(Strategy strategy){
        final long startTime = System.currentTimeMillis();
        strategy.call();
        final long endTime = System.currentTimeMillis();

        final long resultTime = startTime - endTime;
        log.info("resultTime = {}", resultTime);
    }
}
