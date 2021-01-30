package com.namoro.spring.ws.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.namoro.spring.ws.api.model.ChatMessage;



@Component
public class BulletinChatEventListener {
	
	  private static final Logger logger = LoggerFactory.getLogger(BulletinChatEventListener.class); //import loggerFactory and declare private static variable for logger

	    @Autowired 
	    private SimpMessageSendingOperations messagingTemplate; //import simple message sending operations and declare messagingTemplate

	    @EventListener
	    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
	        logger.info("Received a new web socket connection");//handles web socket connect listener connection
	    }

	    @EventListener
	    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
	        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

	        String username = (String) headerAccessor.getSessionAttributes().get("username");
	        if(username != null) { // condition if the username is disconnected from the session
	            logger.info("User Disconnected : " + username); //this where the console displays the name of the username who got disconnected

	            ChatMessage chatMessage = new ChatMessage();
	            chatMessage.setType(ChatMessage.MessageType.LEAVE); // this is where the method when the user leave, it displays the users who left in the chatbox
	            chatMessage.setSender(username);

	            messagingTemplate.convertAndSend("/topic/public", chatMessage); //sends to the chatBox
	        }
	    }

}
