package com.pattern.designpattern.singleton;

public class Singleton {

    private static Singleton singleton = new Singleton(); // 객체 생성시 최초 1회 생성만

    private Singleton(){}

    public static Singleton getInstance(){
        return singleton;
    }
}
