package com.pattern.designpattern.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV1 {

    private Strategy strategy;

    public ContextV1(final Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute(){
        final long startTime = System.currentTimeMillis();
        strategy.call();
        final long endTime = System.currentTimeMillis();

        final long resultTime = startTime - endTime;
        log.info("resultTime = {}", resultTime);
    }
}
