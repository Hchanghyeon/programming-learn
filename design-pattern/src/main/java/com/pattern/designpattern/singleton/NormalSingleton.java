package com.pattern.designpattern.singleton;

public class NormalSingleton {

    private static NormalSingleton normalSingleton;

    private NormalSingleton(){}

    public static synchronized NormalSingleton getInstance(){ // 멀티쓰레드 환경에서 동시성 문제 발생
        if(normalSingleton == null){
            normalSingleton = new NormalSingleton();
        }

        return normalSingleton;
    }
}
