package com.pattern.designpattern.command;

public class SimpleRemoteControl {
    Command slot;
    public SimpleRemoteControl(){}

    public void setSlot(final Command slot) {
        this.slot = slot;
    }

    public void buttonWasPressed(){
        slot.execute();
    }
}
