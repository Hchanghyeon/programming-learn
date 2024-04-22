package com.pattern.designpattern;

import org.junit.jupiter.api.Test;

import com.pattern.designpattern.decorator.Component;
import com.pattern.designpattern.decorator.DecoratorPatternClient;
import com.pattern.designpattern.decorator.MessageDecorator;
import com.pattern.designpattern.decorator.RealComponent;

public class DecoratorPatternTest {

    @Test
    void noDecorator(){
        Component realComponent = new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(realComponent);
        client.execute();
    }

    @Test
    void decorator(){
        Component realComponent = new RealComponent();
        Component messageDecorator = new MessageDecorator(realComponent);
        DecoratorPatternClient client = new DecoratorPatternClient(messageDecorator);
        client.execute();
    }
}
