package com.pattern.designpattern;

import org.junit.jupiter.api.Test;

import com.pattern.designpattern.templatecallback.TimeLogTemplate;

public class TemplateCallBackTest {

    @Test
    void callbackV1(){
        TimeLogTemplate template = new TimeLogTemplate();
        template.execute(() -> System.out.println("비즈니스 로직1 실행"));
        template.execute(() -> System.out.println("비즈니스 로직2 실행"));
    }
}
