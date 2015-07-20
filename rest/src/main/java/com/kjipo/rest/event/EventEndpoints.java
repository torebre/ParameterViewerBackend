package com.kjipo.rest.event;


import com.kjipo.event.EventRepository;
import com.kjipo.event.Events;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@ComponentScan("com.kjipo.data.event")
@EnableAutoConfiguration
@RestController
public class EventEndpoints {
    @Autowired
    private EventRepository eventRepository;

    @RequestMapping(value = "/events/{id}")
    public List<Events> getEvents(@PathVariable(value = "id") long id) {
        return eventRepository.findByLogId(id);
    }

}
