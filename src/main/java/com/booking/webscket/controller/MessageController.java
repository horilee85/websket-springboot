package com.booking.webscket.controller;

/**
 * @author pholv
 * @created 2021-11-18 1:42 PM
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/hello")
    public void send(SimpMessageHeaderAccessor sha, @Payload String username) {
        String message = "Hello from " + sha.getUser().getName();

        simpMessagingTemplate.convertAndSendToUser(username, "/queue/messages", message);
    }

    @MessageMapping("/search_flight")
    public void search_flight(SimpMessageHeaderAccessor sha, @Payload String username) {

        String message = "Search: " + sha.getUser().getName();
        simpMessagingTemplate.convertAndSendToUser(username, "/queue/search_flight", message);
    }
}