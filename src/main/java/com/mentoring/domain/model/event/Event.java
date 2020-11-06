package com.mentoring.domain.model.event;

import com.mentoring.domain.common.model.AbstractBaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "calendar")
public class Event extends AbstractBaseEntity {

  private static final long serialVersionUID = -538781580460070724L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @Column(name = "team", nullable = false, length = 100)
  private String team;

  @Column(name = "title", nullable = false, length = 30)
  private String title;

  @Column(name = "details", nullable = false, length = 45)
  private String details;

  @Column(name = "start", nullable = false, length = 45)
  private String start;

  @Column(name = "end", nullable = false, length = 45)
  private String end;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "createddt", nullable = false)
  private Date createdDt;

  public Event() {
  }

  /**
   * Create new event during registration
   */
  public static Event create(String name, String team, String title, String details, String start, String end) {
    Event event = new Event();
    event.name = name;
    event.team = team;
    event.title = title;
    event.details = details;
    event.start = start;
    event.end = end;
    event.createdDt = new Date();
    return event;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getTeam() {
    return team;
  }

  public String getTitle() {
    return title;
  }

  public String getDetails() {
    return details;
  }

  public String getStart() {
    return start;
  }

  public String getEnd() {
    return end;
  }

  public Date getCreatedDt() {
    return createdDt;
  }

//   @Override
//   public boolean equals(Object o) {
//     if (this == o) return true;
//     if (!(o instanceof Event)) return false;
//     Evnet event = (Event) o;
//     return Objects.equals(username, user.username) &&
//       Objects.equals(emailAddress, user.emailAddress);
//   }

//   @Override
//   public int hashCode() {
//     return Objects.hash(username, emailAddress);
//   }

  @Override
  public String toString() {
    return "Event{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", team='" + team + '\'' +
      ", title='" + title + '\'' +
      ", details='" + details + '\'' +
      ", start='" + start + '\'' +
      ", end='" + end + '\'' +
      ", createdDt=" + createdDt +
      '}';
  }
}