package com.kjipo.websockets;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class SimpleWebSocketMessageSender implements DataUpdateEventTransmitter {
    private final Set<MessageConsumer> messageConsumers;
    private ScheduledExecutorService scheduledExecutorService;
    private final AtomicInteger atomicInteger = new AtomicInteger();

    private static Logger logger = LoggerFactory.getLogger(SimpleWebSocketMessageSender.class);


    public SimpleWebSocketMessageSender() {
        messageConsumers = new CopyOnWriteArraySet<>();
        startService();
    }

    private void startService() {
        scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(() -> {

            System.out.println("Number of message consumers: " + messageConsumers.size());

            int currentValue = atomicInteger.getAndIncrement();
            messageConsumers.stream()
                    .forEach(consumer -> {
                        try {
                            System.out.println("Test30");
                            consumer.receiveMessage(Integer.toString(currentValue));
                        } catch (RuntimeException e) {
                            logger.error("Exception when sending message to consumer", e);
                        }
                    });

        }, 1, 1, TimeUnit.SECONDS);
    }


    public void stopService() {
        scheduledExecutorService.shutdown();
    }

    public void addMessageConsumer(MessageConsumer messageConsumer) {
        messageConsumers.add(messageConsumer);
    }

    public void removeMessageConsumer(MessageConsumer messageConsumer) {
        messageConsumers.remove(messageConsumer);
    }


}
