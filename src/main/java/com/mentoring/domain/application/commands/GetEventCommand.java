package com.mentoring.domain.application.commands;

import org.springframework.util.Assert;

public class GetEventCommand {

  private String start;
  private String end;

  public GetEventCommand(String start, String end) {

    Assert.hasText(start, "Parameter `title` must not be empty");
    Assert.hasText(end, "Parameter `title` must not be empty");

    this.start = start;
    this.end = end;
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
        ", start='" + start + '\'' +
        ", end='" + end + '\'' +
      '}';
  }
}