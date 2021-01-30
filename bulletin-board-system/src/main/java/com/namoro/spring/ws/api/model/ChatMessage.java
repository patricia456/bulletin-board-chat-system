package com.namoro.spring.ws.api.model;

public class ChatMessage {//this is where the message payload that will be exchanged between the clients and the server. 
	  private MessageType type;
	    private String content;
	    private String sender;

	    public enum MessageType {
	        CHAT,
	        JOIN,
	        LEAVE
	    }

	    public MessageType getType() {
	        return type; 
	    }

	    public void setType(MessageType type) {
	        this.type = type;
	    }

	    public String getContent() {
	        return content;
	    }

	    public void setContent(String content) {
	        this.content = content;
	    }

	    public String getSender() {
	        return sender;
	    }

	    public void setSender(String sender) {
	        this.sender = sender;
	    }
}
