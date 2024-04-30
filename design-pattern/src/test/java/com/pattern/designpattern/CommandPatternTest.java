package com.pattern.designpattern;

import org.junit.jupiter.api.Test;

import com.pattern.designpattern.command.Command;
import com.pattern.designpattern.command.Light;
import com.pattern.designpattern.command.LightOnCommand;
import com.pattern.designpattern.command.SimpleRemoteControl;

public class CommandPatternTest {

    @Test
    void commandTest(){
        Light light = new Light();
        Command command = new LightOnCommand(light);
        SimpleRemoteControl simpleRemoteControl = new SimpleRemoteControl();

        simpleRemoteControl.setSlot(command);
        simpleRemoteControl.buttonWasPressed();
    }
}
