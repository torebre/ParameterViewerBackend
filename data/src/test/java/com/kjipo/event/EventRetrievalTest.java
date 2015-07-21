package com.kjipo.event;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {EventRepository.class, Config.class, Events.class})
public class EventRetrievalTest {
    @Autowired
    private EventRepository eventRepository;

    @Test
    public void getEventsInLog() {
        List<Events> events = eventRepository.findByLogId(1);
        Events event1 = new Events(1, 1, 0, 2);
        Events event2 = new Events(2, 1, 1, 5);

        assertThat(events, hasItems(event1, event2));
        assertThat(events.size(), is(2));
    }

}
