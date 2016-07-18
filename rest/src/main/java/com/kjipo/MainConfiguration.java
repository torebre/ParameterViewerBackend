package com.kjipo;

import com.kjipo.client.GreetingService;
import com.kjipo.client.SimpleGreetingService;
import com.kjipo.data.DataStoreMarker;
import com.kjipo.echo.EchoWebSocketHandler;
import com.kjipo.websockets.DataUpdateEventTransmitter;
import com.kjipo.websockets.SimpleWebSocketMessageSender;
import com.kjipo.websockets.events.DataUpdateEndpoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


// @SpringBootApplication same as @Configuration @EnableAutoConfiguration @ComponentScan
@SpringBootApplication(scanBasePackageClasses = {DataStoreMarker.class})
public class MainConfiguration extends SpringBootServletInitializer
        implements WebSocketConfigurer {
    private static final String PARAMETER_UPDATE = "/parameter";


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(echoWebSocketHandler(), PARAMETER_UPDATE)
                .setAllowedOrigins("*"); //.withSockJS();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MainConfiguration.class);
    }

    @Bean
    public DataUpdateEventTransmitter messageSenderService() {
        return new SimpleWebSocketMessageSender();
    }

    @Bean
    public GreetingService greetingService() {
        return new SimpleGreetingService();
    }

    @Bean
    public DataUpdateEndpoint dataUpdateEndpoint() {
        return new DataUpdateEndpoint();
    }

    @Bean
    public WebSocketHandler echoWebSocketHandler() {
        return new EchoWebSocketHandler(messageSenderService());
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        // This is needed for the Value-annotations to resolve their values
        return new PropertySourcesPlaceholderConfigurer();
    }


    public static void main(String[] args) {
        SpringApplication.run(MainConfiguration.class, args);
    }

}
