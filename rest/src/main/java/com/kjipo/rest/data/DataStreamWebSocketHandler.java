package com.kjipo.rest.data;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;



public class DataStreamWebSocketHandler extends AbstractWebSocketHandler {
    private static Logger logger = LoggerFactory.getLogger(DataStreamWebSocketHandler.class);

    @Autowired
    private DataStreamService dataStreamService;



    public DataStreamWebSocketHandler() {

    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        System.out.println("Test11");
        logger.debug("Opened new session in instance {}", this);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws Exception {
        // TODO Handle message

//            session.sendMessage(new TextMessage(echoMessage));
    }


    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        // TODO Handle message


    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception)
            throws Exception {
        session.close(CloseStatus.SERVER_ERROR);
    }

}



