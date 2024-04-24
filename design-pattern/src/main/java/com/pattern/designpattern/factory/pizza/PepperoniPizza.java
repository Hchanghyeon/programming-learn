package com.pattern.designpattern.factory.pizza;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PepperoniPizza implements Pizza {

    @Override
    public void prepare() {
        log.info("페페로니피자 준비중");
    }

    @Override
    public void bake() {
        log.info("페페로니피자 굽는중");
    }

    @Override
    public void cut() {
        log.info("페페로니피자 자르는중");
    }

    @Override
    public void box() {
        log.info("페페로니피자 포장중");
    }
}
