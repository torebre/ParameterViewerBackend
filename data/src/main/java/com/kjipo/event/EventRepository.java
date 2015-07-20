package com.kjipo.event;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface EventRepository extends JpaRepository<Events, Long> {

    // The query methods will be implemented automatically by Spring
    // based on the method name

    List<Events> findByLogId(long logId);

}
