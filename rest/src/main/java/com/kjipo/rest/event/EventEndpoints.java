package com.kjipo.rest.event;


import com.kjipo.event.EventRepository;
import com.kjipo.event.Events;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class EventEndpoints {
    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/events/{id}")
    public List<Events> getEvents(@PathVariable(value = "id") long id) {
        List<Events> events = eventRepository.findByLogId(id);

        System.out.println("Events: " +events);

        return events;


    }

}
