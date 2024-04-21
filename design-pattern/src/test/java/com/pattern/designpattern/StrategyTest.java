package com.pattern.designpattern;

import org.junit.jupiter.api.Test;

import com.pattern.designpattern.strategy.ContextV1;
import com.pattern.designpattern.strategy.Logic1;
import com.pattern.designpattern.strategy.Logic2;

public class StrategyTest {

    @Test
    void strategyV1(){
        Logic1 logic1 = new Logic1();
        ContextV1 contextV1 = new ContextV1(logic1);
        contextV1.execute();

        Logic2 logic2 = new Logic2();
        ContextV1 contextV2 = new ContextV1(logic2);
        contextV2.execute();
    }
}
