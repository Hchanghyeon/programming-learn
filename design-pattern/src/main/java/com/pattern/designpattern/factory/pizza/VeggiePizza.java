package com.pattern.designpattern.factory.pizza;

import com.pattern.designpattern.factory.pizza.Pizza;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VeggiePizza implements Pizza {

    @Override
    public void prepare() {
        log.info("베지피자 준비중");
    }

    @Override
    public void bake() {
        log.info("베지피자 굽는중");
    }

    @Override
    public void cut() {
        log.info("베지피자 자르는중");
    }

    @Override
    public void box() {
        log.info("베지피자 포장중");
    }
}
