package com.pattern.designpattern.observer;

public interface Observer {
    void update(float temp, float humidity, float pressure); // 모든 옵저버 클래스에서 구현 필요

}
