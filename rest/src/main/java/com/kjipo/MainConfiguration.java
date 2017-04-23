package com.kjipo;

import com.kjipo.client.GreetingService;
import com.kjipo.client.SimpleGreetingService;
import com.kjipo.data.DataStoreMarker;
import com.kjipo.echo.EchoWebSocketHandler;
import com.kjipo.rest.data.DataBlockHttpMessageConverter;
import com.kjipo.websockets.DataUpdateEventTransmitter;
import com.kjipo.websockets.SimpleWebSocketMessageSender;
import com.kjipo.websockets.events.DataUpdateEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;


// @SpringBootApplication same as @Configuration @EnableAutoConfiguration @ComponentScan
@SpringBootApplication //(scanBasePackageClasses = {DataStoreMarker.class})
@EnableJpaRepositories
//@EnableWebSocketMessageBroker
@EnableWebSocket
public class MainConfiguration extends SpringBootServletInitializer
        implements WebSocketConfigurer {
    private static final String PARAMETER_UPDATE = "/parameter";

    @Autowired
    private DataSource dataSource;


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

//    @Bean
//    public ServerEndpointExporter serverEndpointExporter() {
//        return new ServerEndpointExporter();
//    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        // This is needed for the Value-annotations to resolve their values
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    DataBlockHttpMessageConverter dataBlockHttpMessageConverter() {
        return new DataBlockHttpMessageConverter();
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.kjipo.event");

        // TODO Is there another way to attach the data source to the EntityManagerFactory?
        factory.setDataSource(dataSource);
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        // This is to avoid problems with CORS when doing tests
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };

    }

    public static void main(String[] args) {
        SpringApplication.run(MainConfiguration.class, args);
    }

}
