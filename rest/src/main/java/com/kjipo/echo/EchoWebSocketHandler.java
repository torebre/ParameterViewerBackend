/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kjipo.echo;

import com.kjipo.websockets.MessageConsumer;
import com.kjipo.websockets.SimpleWebSocketMessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;



public class EchoWebSocketHandler extends TextWebSocketHandler {

	private static Logger logger = LoggerFactory.getLogger(EchoWebSocketHandler.class);

	private final SimpleWebSocketMessageSender simpleWebSocketMessageSender;
	private final ConcurrentHashMap<String, MessageConsumer> sessionIdMessageConsumerMap = new ConcurrentHashMap<>();

	@Autowired
	public EchoWebSocketHandler(SimpleWebSocketMessageSender simpleWebSocketMessageSender) {
		this.simpleWebSocketMessageSender = simpleWebSocketMessageSender;
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		logger.debug("Opened new session in instance " + this);

		MessageConsumer messageConsumer = message -> {
            try {
				System.out.println("Sending data to: " +session.getId());
				session.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

		sessionIdMessageConsumerMap.put(session.getId(), messageConsumer);
		simpleWebSocketMessageSender.addMessageConsumer(messageConsumer);
	}

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message)
			throws Exception {
        System.out.println("Test20");
//        String echoMessage = this.messageSenderService.getMessage(message.getPayload());
//		logger.info(echoMessage);
//		session.sendMessage(new TextMessage(echoMessage));
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception)
			throws Exception {
		System.out.println("Closing session");
		session.close(CloseStatus.SERVER_ERROR);
		simpleWebSocketMessageSender.removeMessageConsumer(sessionIdMessageConsumerMap.get(session.getId()));
	}

}
