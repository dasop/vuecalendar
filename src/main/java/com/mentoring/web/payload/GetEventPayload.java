package com.mentoring.web.payload;

import com.mentoring.domain.application.commands.GetEventCommand;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class GetEventPayload {
   
    @Size(min = 10, max = 10, message = "start must be 10 characters")
    @NotNull
    private String start;

    @Size(min = 10, max = 10, message = "end must be 10 characters")
    @NotNull
    private String end;

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
    public GetEventCommand toCommand() {
        return new GetEventCommand(this.start, this.end);
      }
}