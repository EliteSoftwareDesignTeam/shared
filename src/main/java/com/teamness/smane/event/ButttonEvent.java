package com.teamness.smane.event;

public class ButttonEvent {

    public final ButtonAction action;

    public ButttonEvent(ButtonAction action) {
        this.action = action;
    }

    public static enum ButtonAction {
        PRESSED, RELEASED
    }

}
