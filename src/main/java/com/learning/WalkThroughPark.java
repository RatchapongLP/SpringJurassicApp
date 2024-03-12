package com.learning;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class WalkThroughPark extends ApplicationEvent {

    public WalkThroughPark(Object source) {
        super(source);
    }

    public WalkThroughPark(Object source, Clock clock) {
        super(source, clock);
    }

    public String toString() {
        return "Walk Through Park event occurs";
    }
}
