package com.pattern.designpattern;

import org.junit.jupiter.api.Test;

import com.pattern.designpattern.factory.PizzaStore;
import com.pattern.designpattern.factory.SimplePizzaFactory;

public class FactoryPatternTest {

    @Test
    void factoryPattern(){
        PizzaStore pizzaStore = new PizzaStore(new SimplePizzaFactory());
        pizzaStore.orderPizza("cheese");
        pizzaStore.orderPizza("pepperoni");
        pizzaStore.orderPizza("veggie");
        pizzaStore.orderPizza("clam");
    }
}
