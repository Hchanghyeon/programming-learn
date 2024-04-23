package com.pattern.designpattern.observer;

public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers(); // 주제의 상태가 변경되었을 때 모든 옵저버들에게 알림

}
