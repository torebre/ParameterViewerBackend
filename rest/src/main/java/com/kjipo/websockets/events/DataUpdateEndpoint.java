package com.kjipo.websockets.events;


import com.kjipo.rest.data.DataStreamWebSocketHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


@ServerEndpoint("/events")
public class DataUpdateEndpoint {
    private static Logger LOGGER = LoggerFactory.getLogger(DataUpdateEndpoint.class);

    @OnOpen
    public void onOpen(Session session) throws IOException {
        LOGGER.info("OnOpen");

        session.getBasicRemote().sendText("onOpen");
    }

    @OnMessage
    public String echo(String message) {
        LOGGER.info("OnMessage");
        return message + " (from your server)";
    }

    @OnError
    public void onError(Throwable t) {
        LOGGER.info("OnError");
        t.printStackTrace();
    }

    @OnClose
    public void onClose(Session session) {
        LOGGER.info("OnClose");

    }






}
