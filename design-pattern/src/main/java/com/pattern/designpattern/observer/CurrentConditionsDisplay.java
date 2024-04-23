package com.pattern.designpattern.observer;

// 구독자
public class CurrentConditionsDisplay implements Observer, DisplayElement{

    private float temperature;
    private float humidity;
    private final WeatherData weatherData;

    public CurrentConditionsDisplay(final WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this); // weatherData에 대한 구독
    }

    @Override
    public void display() {
        System.out.println("현재 상태: 온도 " + temperature + "F, 습도 " + humidity + "%");
    }

    @Override
    public void update(final float temp, final float humidity, final float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        display();
    }
}
