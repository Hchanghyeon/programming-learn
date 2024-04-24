package com.pattern.designpattern.factory;

import com.pattern.designpattern.factory.pizza.CheesePizza;
import com.pattern.designpattern.factory.pizza.ClamPizza;
import com.pattern.designpattern.factory.pizza.PepperoniPizza;
import com.pattern.designpattern.factory.pizza.Pizza;
import com.pattern.designpattern.factory.pizza.VeggiePizza;

public class SimplePizzaFactory {

    public Pizza createPizza(final String type) {

        if(type.equals("cheese")){
            return new CheesePizza();
        }
        if(type.equals("pepperoni")){
            return new PepperoniPizza();
        }
        if(type.equals("clam")){
            return new ClamPizza();
        }
        if(type.equals("veggie")){
            return new VeggiePizza();
        }

        throw new RuntimeException("없는 피자입니다.");
    }
}


