package com.pattern.designpattern.factory.pizza;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CheesePizza implements Pizza {

    @Override
    public void prepare() {
        log.info("치즈피자 준비중");
    }

    @Override
    public void bake() {
        log.info("치즈피자 굽는중");
    }

    @Override
    public void cut() {
        log.info("치즈피자 자르는중");
    }

    @Override
    public void box() {
        log.info("치즈피자 포장중");
    }
}
