package com.teamness.smane.event;

public class CaneEvents {

    public static final EventChannel LOCAL = new EventChannel(), BT = new EventChannel(), ALL = new EventChannel(EventChannel.EventPriority.MEDIUM, EventChannel.EventPriority.HIGH, LOCAL, BT);

}
