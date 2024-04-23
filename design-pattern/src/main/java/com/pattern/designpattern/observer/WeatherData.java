package com.pattern.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

public class WeatherData implements Subject {

    private List<Observer> observers = new ArrayList<>();
    private float temperature;
    private float humidity;
    private float pressure;

    @Override
    public void registerObserver(final Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(final Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(observer -> observer.update(temperature, humidity, pressure));
    }

    public void measurementsChanged(){
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged(); // setMeasurements로 기상 스테이션으로부터 변경된 값을 인지하여 옵저버들에게 알리는 코드
    }
}
