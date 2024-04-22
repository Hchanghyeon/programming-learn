package com.pattern.designpattern.decorator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator implements Component{

    private Component realComponent;

    public MessageDecorator(final Component realComponent) {
        this.realComponent = realComponent;
    }

    @Override
    public String operation() {
        log.info("프록시 객체 호출");
        String result = realComponent.operation();
        return result + "hi";
    }
}
