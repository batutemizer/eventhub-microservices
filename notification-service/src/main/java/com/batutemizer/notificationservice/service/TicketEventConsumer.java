package com.batutemizer.notificationservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TicketEventConsumer {

    @KafkaListener(
            topics = "ticket-created",
            groupId = "notification-service"
    )
    public void consume(String message) {

        System.out.println("Kafka'dan mesaj geldi: " + message);
    }
}