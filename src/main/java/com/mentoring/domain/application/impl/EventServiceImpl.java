package com.mentoring.domain.application.impl;

import com.mentoring.domain.application.EventService;
import com.mentoring.domain.application.commands.EventCommand;
import com.mentoring.domain.application.commands.GetEventCommand;
import com.mentoring.domain.model.event.EventException;
import com.mentoring.domain.model.event.EventManagement;
import com.mentoring.domain.model.event.Event;
// import com.mentoring.domain.model.user.events.UserRegisteredEvent;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

import java.util.List;

@Service
@Transactional
public class EventServiceImpl implements EventService {

  private EventManagement eventManagement;

  public EventServiceImpl(EventManagement eventManagement) {
    this.eventManagement = eventManagement;

  }

  @Override
  public void setEvent(EventCommand command) throws EventException {
    Assert.notNull(command, "Parameter `command` must not be null");
    Event newEvent = eventManagement.setEvent(
      command.getName(),
      command.getTeam(),
      command.getTitle(),
      command.getDetails(),
      command.getStart(),
      command.getEnd()
    );
  }
  
  @Override
  public List<Event> getEvent(GetEventCommand command) throws EventException {
    Assert.notNull(command, "Parameter `command` must not be null");

    List<Event> events = eventManagement.getEvent(command.getStart(), command.getEnd());

    return events;
  }

}