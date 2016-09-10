package com.kjipo.event;


import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EventRepository.class, Config.class, Events.class})
public class EventRetrievalIntegrationTest {
    @Autowired
    private EventRepository eventRepository;

    @Test
    public void getEventsInLog() {
        List<Events> events = eventRepository.findByLogId(1);
        Events event1 = new Events(1L, 1, 0, 2);
        Events event2 = new Events(2L, 1, 1, 5);

        Assertions.assertThat(events).containsOnly(event1, event2);
    }

}
