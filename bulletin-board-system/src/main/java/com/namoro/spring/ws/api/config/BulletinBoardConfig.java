package com.namoro.spring.ws.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration //this is used to enable our WebSocket server
@EnableWebSocketMessageBroker //this is use  interface and provide implementation for some of its methods to configure the websocket connection.
public class BulletinBoardConfig  implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) { //this is where the first method that we register a websocket endpoint that the clients will use to connect to our websocket server.
        registry.addEndpoint("/namoro").withSockJS();//used to enable fallback options for browsers that don’t support websocket.
    }
    

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");// where messages whose destination starts with “/app” should be routed to message-handling methods
        registry.enableSimpleBroker("/topic");   // where it enables a simple in-memory broker


    }

}
