package com.booking.webscket.schedules;

/**
 * @author pholv
 * @created 2021-11-18 2:09 PM
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Service
public class ScheduledPushMessages {

    private final SimpMessagingTemplate simpMessagingTemplate;
    @Value("${data.users:pholv,xonv,khoakd}")
    private String[] users;

    int rnd(int size) {

        return (int) (Math.random() * size);
    }

    public ScheduledPushMessages(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;

    }

    @Scheduled(fixedRate = 10000)
    public void sendMessage() {
        Random objGenerator = new Random();
        int number = objGenerator.nextInt(3);
        if (number > 2 || number < 0) {
            number = 0;
        }

        final String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        simpMessagingTemplate.convertAndSendToUser(users[number], "/queue/messages", "Hello, test:" + time);
    }

    @Scheduled(fixedRate = 5000)
    public void searchFlight() {
        Random objGenerator = new Random();
        int number = objGenerator.nextInt(3);
        if (number > 2 || number < 0) {
            number = 0;
        }

        final String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        simpMessagingTemplate.convertAndSendToUser(users[0], "/queue/search_flight", "Hello, search_flight:" + time);
    }

}
