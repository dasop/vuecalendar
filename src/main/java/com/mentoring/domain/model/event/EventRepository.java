package com.mentoring.domain.model.event;

import java.util.List;

/**
 * Event repository interface
 */
public interface EventRepository{

  /**
   * Save a new event
   *
   * @param event the event instance to be saved
   * @return the newly save event
   */
  void save(Event event);

  List<Event> getevents(String start, String end);
}