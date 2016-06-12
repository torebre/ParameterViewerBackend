package com.kjipo.client;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;



public class SimpleTextWebSocketClient extends TextWebSocketHandler {
    protected Log logger = LogFactory.getLog(SimpleClientWebSocketHandler.class);

    @Autowired
    public SimpleTextWebSocketClient() {

    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("Test51");
        TextMessage message = new TextMessage("Test50");
        session.sendMessage(message);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws Exception {
        this.logger.info("Received: " + message);
        session.close();
    }

}
