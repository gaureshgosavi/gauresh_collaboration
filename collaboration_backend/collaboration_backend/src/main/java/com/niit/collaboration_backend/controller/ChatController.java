package com.niit.collaboration_backend.controller;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.niit.collaboration_backend.model.Message;
import com.niit.collaboration_backend.model.OutputMessage;

@Controller
public class ChatController {

	@MessageMapping("/chat")
	@SendTo("/topic/message")
	public OutputMessage sendMessage(Message message) {
		System.out.println(message.getMessage());
		return new OutputMessage(message, new Date());
	}
	
	//allows us to broadcast a message to /topic/message when a message enters the message broker /app/chat
}
