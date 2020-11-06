package com.mentoring.web.payload;

import com.mentoring.domain.application.commands.EventCommand;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EventPayload {
    @Size(min = 3, max = 50, message = "title must be between 3 and 50 characters")
    @NotNull
    private String title;

    @Size(min = 3, max = 300, message = "details must be between 3 and 300 characters")
    @NotNull
    private String details;

    @Size(min = 10, max = 10, message = "start must be 10 characters")
    @NotNull
    private String start;

    @Size(min = 10, max = 10, message = "end must be 10 characters")
    @NotNull
    private String end;

    @Size(min = 3, max = 50, message = "name must be between 3 and 50 characters")
    @NotNull
    private String name;

    @Size(min = 3, max = 50, message = "team must be between 3 and 50 characters")
    @NotNull
    private String team;

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getDetails(){
        return details;
    }

    public void setDetails(String details){
        this.details = details;
    }

    public String getStart(){
        return start;
    }

    public void setStart(String start){
        this.start = start;
    }

    public String getEnd(){
        return end;
    }

    public void setEnd(String end){
        this.end = end;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getTeam(){
        return team;
    }

    public void setTeam(String team){
        this.team = team;
    }

    public EventCommand toCommand() {
        return new EventCommand(this.title, this.details, this.start, this.end, this.name, this.team);
      }
}