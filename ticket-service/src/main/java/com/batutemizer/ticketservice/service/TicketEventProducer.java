package com.batutemizer.ticketservice.service;

import com.batutemizer.ticketservice.dto.event.TicketCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketEventProducer {

    private final KafkaTemplate<String, TicketCreatedEvent> kafkaTemplate;

    public void sendTicketCreatedEvent(TicketCreatedEvent event) {
        kafkaTemplate.send("ticket-created", event);
    }
}