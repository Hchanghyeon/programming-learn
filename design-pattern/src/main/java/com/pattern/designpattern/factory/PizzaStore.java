package com.pattern.designpattern.factory;
import com.pattern.designpattern.factory.pizza.Pizza;

public class PizzaStore {
    SimplePizzaFactory factory;

    public PizzaStore(SimplePizzaFactory factory){
        this.factory = factory;
    }

    public Pizza orderPizza(String type){
        Pizza pizza = factory.createPizza(type); // 팩토리 추가로 인해 비즈시느 로직이 포함된 곳의 코드는 변경하지 않아도 됨

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }
}
