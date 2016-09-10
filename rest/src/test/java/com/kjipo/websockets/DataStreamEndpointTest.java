package com.kjipo.websockets;


import com.kjipo.MainConfiguration;
import com.kjipo.client.GreetingService;
import com.kjipo.client.SimpleClientWebSocketHandler;
import com.kjipo.client.SimpleGreetingService;
import com.kjipo.client.SimpleTextWebSocketClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.sockjs.client.RestTemplateXhrTransport;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MainConfiguration.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DataStreamEndpointTest {

    @Value("${local.server.port}")
    private int port = 1234;


    private static Log logger = LogFactory.getLog(DataStreamEndpointTest.class);


    @Test
    public void connectTest() throws ExecutionException, InterruptedException {
//        WebSocketClient transport = new StandardWebSocketClient();
//        WebSocketStompClient stompClient = new WebSocketStompClient(transport);
//        stompClient.setMessageConverter(new StringMessageConverter());

//        String url = "ws://127.0.0.1:9000";

        System.out.println("Port: " +port);

//        String url = "ws://127.0.0.1:" +port;
//        StompSessionHandler handler = new MySessionHandler();
//        ListenableFuture<StompSession> sessionFuture = stompClient.connect(url, handler);
//
//        StompSession stompSession = sessionFuture.get();
//        stompSession.send("myHandler", "test2");


        ConfigurableApplicationContext context = new SpringApplicationBuilder(
                ClientConfiguration.class, PropertyPlaceholderAutoConfiguration.class)
                .properties("websocket.uri:ws://localhost:" + this.port
                        + "/ws/myHandler/websocket")
                .run("--spring.main.web_environment=false");
        long count = context.getBean(ClientConfiguration.class).latch.getCount();
        AtomicReference<String> messagePayloadReference = context
                .getBean(ClientConfiguration.class).messagePayload;
        context.close();
    }


    @Test
    public void echoEndpoint() throws Exception {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(
                ClientConfiguration.class,
                PropertyPlaceholderAutoConfiguration.class)
                .properties("websocket.uri:ws://localhost:" + this.port
                        + "/echo/websocket")
                .run("--spring.main.web_environment=false");
        long count = context.getBean(ClientConfiguration.class).latch.getCount();
        AtomicReference<String> messagePayloadReference = context
                .getBean(ClientConfiguration.class).messagePayload;
        context.close();
        assertEquals(0, count);
        assertEquals("Did you say \"Hello world!\"?", messagePayloadReference.get());
    }


    @Test
    public void testSampleWebSocketsApplication() {
        List<Transport> transports = new ArrayList<>(1);
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        transports.add(new RestTemplateXhrTransport());

        SockJsClient sockJsClient = new SockJsClient(transports);
        SimpleTextWebSocketClient simpleTextWebSocketClient = new SimpleTextWebSocketClient();
        sockJsClient.doHandshake(simpleTextWebSocketClient, "ws://localhost:9000/echo");
    }


    public class MySessionHandler extends StompSessionHandlerAdapter {

        @Override
        public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
            System.out.println("Test20");
        }

    }


    @Configuration
    public static class ClientConfiguration implements CommandLineRunner {

        @Value("${websocket.uri}")
        private String webSocketUri;

        final CountDownLatch latch = new CountDownLatch(1);

        final AtomicReference<String> messagePayload = new AtomicReference<String>();

        @Override
        public void run(String... args) throws Exception {
            logger.info("Waiting for response: latch=" + this.latch.getCount());
            if (this.latch.await(10, TimeUnit.SECONDS)) {
                logger.info("Got response: " + this.messagePayload.get());
            }
            else {
                logger.info("Response not received: latch=" + this.latch.getCount());
            }
        }

        @Bean
        public WebSocketConnectionManager wsConnectionManager() {
            WebSocketConnectionManager manager = new WebSocketConnectionManager(client(),
                    handler(), this.webSocketUri);
            manager.setAutoStartup(true);
            return manager;
        }

        @Bean
        public StandardWebSocketClient client() {
            return new StandardWebSocketClient();
        }

        @Bean
        public SimpleClientWebSocketHandler handler() {
            return new SimpleClientWebSocketHandler(greetingService(), this.latch,
                    this.messagePayload);
        }

        @Bean
        public GreetingService greetingService() {
            return new SimpleGreetingService();
        }
    }


}
