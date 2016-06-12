package com.kjipo.rest.data;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.sql.DataSource;



@Configuration
@EnableAutoConfiguration
@EnableWebSocket
public class DataStreamEndpoints extends SpringBootServletInitializer
        implements WebSocketConfigurer {



    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DataStreamEndpoints.class);
    }


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(getDataStreamWebSocketHandler(), "/stream").withSockJS();
    }

    @Bean
    public DataStreamWebSocketHandler getDataStreamWebSocketHandler() {
        return new DataStreamWebSocketHandler();
    }


}
