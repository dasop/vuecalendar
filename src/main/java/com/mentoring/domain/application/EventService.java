package com.mentoring.domain.application;

import com.mentoring.domain.application.commands.EventCommand;
import com.mentoring.domain.application.commands.GetEventCommand;
import com.mentoring.domain.model.event.EventException;

import com.mentoring.domain.model.event.Event;
import java.util.List;

public interface EventService {

  /**
   * Get the operation events.
   *
   * @param command instance of <code>GetEventCommand</code>
   * @throws eventException when getting the events is failed. Possible reasons are:
   *                               1)
   *                               2) 
   */

  void setEvent(EventCommand command) throws EventException;

  List<Event> getEvent(GetEventCommand command) throws EventException;
}