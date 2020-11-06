package com.mentoring.domain.model.event;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 
 */
@Component
public class EventManagement {

  private EventRepository repository;

  public EventManagement(EventRepository repository) {
    this.repository = repository;
  }

  public Event setEvent(String title, String details, String start, String end, String name, String team)
    throws EventException {
    
    Event newEvent = Event.create(title, details, start, end, name, team);
    repository.save(newEvent);
    return newEvent;
  }

  public List<Event> getEvent(String start, String end) throws EventException {    
    List<Event> events = repository.getevents(start,end);
    return events;
  }
}