package com.pattern.designpattern.singleton;

public class ClassicSingleton {

    private static ClassicSingleton classicSingleton;

    private ClassicSingleton(){}

    public static ClassicSingleton getInstance(){ // 멀티쓰레드 환경에서 동시성 문제 발생
        if(classicSingleton == null){
            classicSingleton = new ClassicSingleton();
        }

        return classicSingleton;
    }
}
