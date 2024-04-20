package com.pattern.designpattern;

import org.junit.jupiter.api.Test;

import com.pattern.designpattern.template.AbstractTemplate;
import com.pattern.designpattern.template.Logic1;
import com.pattern.designpattern.template.Logic2;

public class TemplateTest {

    @Test
    void templateMethodTest(){
        final AbstractTemplate abstractTemplate1 = new Logic1();
        abstractTemplate1.execute();

        final AbstractTemplate abstractTemplate2 = new Logic2();
        abstractTemplate2.execute();
    }
}
