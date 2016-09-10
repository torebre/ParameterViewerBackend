package com.kjipo.event;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Spring will set up an embedded database automatically here.
 *
 * The {@code @DataJpaTest}-annotation requires that a class
 * with a {@code @SpringBootApplication}-annotation is found.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class EventRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EventRepository repository;

    @Test
    public void eventsStored() throws Exception {
        Events event1 = new Events(1, 3, 4);
        this.entityManager.persist(event1);
        Events event2 = new Events(1, 4, 5);
        this.entityManager.persist(event2);

        List<Events> events = repository.findByLogId(1);
        assertThat(events).containsOnly(event1, event2);
    }

}