package com.pattern.designpattern;

import org.junit.jupiter.api.Test;

import com.pattern.designpattern.observer.CurrentConditionsDisplay;
import com.pattern.designpattern.observer.WeatherData;

public class ObserverPatternTest {

    @Test
    void observerPatternTest(){
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);

        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(60, 25, 30.4f);
        weatherData.setMeasurements(30, 30, 30.4f);
    }
}
