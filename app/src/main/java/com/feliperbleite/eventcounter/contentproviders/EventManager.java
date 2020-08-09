package com.feliperbleite.eventcounter.contentproviders;

import com.feliperbleite.eventcounter.domain.Event;

import java.util.ArrayList;
import java.util.List;

public class EventManager {

    private static List<Event> events = new ArrayList<>();
    public EventManager () {
        throw new UnsupportedOperationException("This is a singleton class");
    }

    public static Integer addEvent() {
        events.add(new Event());
        return events.size();
    }

    public static Integer getEventsCount() {
        return events.size();
    }

    public static List<Event> getAllEvents() {
        return new ArrayList<>(events);
    }
}
