package com.batutemizer.ticketservice.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketCreatedEvent {

    private Long ticketId;
    private Long userId;
    private Long eventId;
}