package com.mentoring.domain.application.commands;

import org.springframework.util.Assert;

public class EventCommand {

  private String title;
  private String details;
  private String start;
  private String end;
  private String name;
  private String team;

  public EventCommand(String title, String details, String start, String end, String name, String team) {
    // Assert.hasText(name, "Parameter `name` must not be empty");
    // Assert.hasText(team, "Parameter `team` must not be empty");
    // Assert.hasText(title, "Parameter `title` must not be empty");
    // Assert.hasText(start, "Parameter `title` must not be empty");
    // Assert.hasText(end, "Parameter `title` must not be empty");

    this.name = name;
    this.team = team;
    this.title = title;
    this.details = details;
    this.start = start;
    this.end = end;
  }

  public String getName() {
    return this.name;
  }

  public String getTeam() {
    return this.team;
  }

  public String getTitle() {
    return this.title;
  }

  public String getDetails() {
    return this.details;
  }

  public String getStart() {
    return this.start;
  }

  public String getEnd() {
    return this.end;
  }

//   @Override
//   public boolean equals(Object o) {
//     if (this == o) return true;
//     if (o == null || getClass() != o.getClass()) return false;
//     RegistrationCommand that = (RegistrationCommand) o;
//     if (username != null ? !username.equals(that.username) : that.username != null) return false;
//     if (emailAddress != null ? !emailAddress.equals(that.emailAddress) : that.emailAddress != null) return false;
//     return password != null ? password.equals(that.password) : that.password == null;
//   }

//   @Override
//   public int hashCode() {
//     int result = username != null ? username.hashCode() : 0;
//     result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0);
//     result = 31 * result + (password != null ? password.hashCode() : 0);
//     return result;
//   }

  @Override
  public String toString() {
    return "EventCommand{" +
        ", name='" + name + '\'' +
        ", team='" + team + '\'' +
        ", title='" + title + '\'' +
        ", details='" + details + '\'' +
        ", start='" + start + '\'' +
        ", end='" + end + '\'' +
      '}';
  }
}