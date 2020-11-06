package com.mentoring.web.apis;

import com.mentoring.domain.application.EventService;
// import com.mentoring.domain.model.user.EmailAddressExistsException;
// import com.mentoring.domain.model.user.RegistrationException;
// import com.mentoring.domain.model.user.UsernameExistsException;
import com.mentoring.web.payload.EventPayload;
import com.mentoring.web.payload.GetEventPayload;
import com.mentoring.web.results.ApiResult;
import com.mentoring.web.results.Result;
import com.mentoring.domain.model.event.EventException;
import com.mentoring.domain.model.event.Event;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

import java.util.List;

@Controller
public class EventApiController {

    private EventService service;
    
    public EventApiController(EventService service) {
        this.service = service;
    }

    // @GetMapping("/api/getevnet")
    // public ResponseEntity<ApiResult> getevent(){
    //     try{
    //         service.getevent()
    //     }catch(geteventException e){
    //         return ;
    //     }
    // }

    @PostMapping("/api/setevent")
    public ResponseEntity<ApiResult> setEvent(@Valid @RequestBody EventPayload payload){
        try{
            service.setEvent(payload.toCommand());
            return Result.created();
        }catch(EventException e){
            String errorMessage = "SetEvent failed";
            return Result.failure(errorMessage);
        }
    }


    @PostMapping("/api/getevent")
    public ResponseEntity<ApiResult> getEvent(@Valid @RequestBody GetEventPayload payload){
        try{
            List<Event> events = service.getEvent(payload.toCommand());
            if(events == null){
                return Result.notFound();
            }
            ApiResult result = ApiResult.blank().add("events", events);
            return Result.ok(result);
        }catch(EventException e){
            String errorMessage = "GetEvents failed";
            return Result.failure(errorMessage);
        }
    }
}