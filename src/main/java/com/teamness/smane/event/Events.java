package com.teamness.smane.event;

import com.teamness.smane.Pair;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by samtebbs on 13/02/2018.
 */

public class Events {

    public enum EventPriority {
        HIGH, MEDIUM, LOW
    }

    private static final Map<Class<? extends Event>, Map<EventPriority, List<Pair<Method, Object>>>> subscribers = new HashMap<>();
    private static final Map<EventPriority, List<Pair<Method, Object>>> defaultSubscribers = new HashMap<EventPriority, List<Pair<Method,Object>>>() {{
        for(EventPriority p : EventPriority.values()) put(p, new ArrayList<>());
    }};

    /**
     * Trigger an event and its listeners
     * @param event the event to trigger
     * @param <T> the type of the event
     */
    public static <T extends Event> void trigger(T event) {
        if(subscribers.containsKey(event.getClass())) {
            Map<EventPriority, List<Pair<Method, Object>>> consumers = subscribers.get(event.getClass());
            for(EventPriority priority : EventPriority.values()) {
                for(Pair<Method, Object> consumer : consumers.get(priority)) invoke(consumer, event);
            }
        }
        for(EventPriority priority : EventPriority.values()) for(Pair<Method, Object> consumer : defaultSubscribers.get(priority)) invoke(consumer, event);
    }

    private static <T extends Event> void invoke(Pair<Method, Object> consumer, T event) {
        try {
            consumer.first.invoke(consumer.second, event);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * Register an event listener
     * @param priority the listener's priority
     * @param eventClass the class of the event to listen for
     * @param method the name of the method to invoke when the event is triggered
     * @param caller the object to call the method from
     * @param <T> the type of the event
     */
    public static <T extends Event> boolean on(EventPriority priority, Class<T> eventClass, String method, Object caller) {
        Pair<Method, Object> consumer = newConsumer(caller, method, eventClass);
        if(consumer == null) return false;
        if(subscribers.containsKey(eventClass)) subscribers.get(eventClass).get(priority).add(consumer);
        else {
            Map<EventPriority, List<Pair<Method, Object>>> map = new HashMap<EventPriority, List<Pair<Method, Object>>>() {{
                for(EventPriority priority : EventPriority.values()) {
                    put(priority, new ArrayList<>());
                }
            }};
            map.get(priority).add(consumer);
            subscribers.put(eventClass, map);
        }
        return true;
    }

    public boolean onAny(EventPriority priority, String method, Object caller) {
        Pair<Method, Object> consumer = newConsumer(caller, method, Event.class);
        if(consumer == null) return false;
        defaultSubscribers.get(priority).add(consumer);
        return true;
    }

    /**
     * Creates a consumer pair
     * @param caller the object to call the method from
     * @param methodName the name of the method to call
     * @param eventClass the event class that the method takes
     * @param <T> the type of the event
     * @return null if a consumer pair can't be created (no methods with that name and parameter class), else the new consumer pair
     */
    public static <T extends Event> Pair<Method, Object> newConsumer(Object caller, String methodName, Class<T> eventClass) {
        if(caller != null) {
            try {
                Method method = caller.getClass().getMethod(methodName, eventClass);
                return new Pair<>(method, caller);
            } catch (NoSuchMethodException ignored) {}
        }
        return null;
    }

    /**
     * Same as {@link Events#on(EventPriority, Class, String, Object)} but with medium priority
     * @param eventClass the class of the event to listen for
     * @param method the method name to invoke when the event is triggered
     * @param caller the object to call the method from
     * @param <T> the type of the event
     */
    public static <T extends Event> boolean on(Class<T> eventClass, String method, Object caller) {
        return on(EventPriority.MEDIUM, eventClass, method, caller);
    }

}
