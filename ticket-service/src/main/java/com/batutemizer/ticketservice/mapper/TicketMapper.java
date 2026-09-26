package com.batutemizer.ticketservice.mapper;

import com.batutemizer.ticketservice.dto.request.TicketCreateRequest;
import com.batutemizer.ticketservice.dto.response.TicketResponse;
import com.batutemizer.ticketservice.entity.TicketEntity;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    public TicketEntity toEntity(TicketCreateRequest ticketCreateRequest) {
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setEventId(ticketCreateRequest.getEventId());
        return ticketEntity;
    }

    public TicketResponse toResponse(TicketEntity ticketEntity) {
        TicketResponse ticketResponse = new TicketResponse();
        ticketResponse.setId(ticketEntity.getId());
        ticketResponse.setEventId(ticketEntity.getEventId());
        ticketResponse.setCreatedAt(ticketEntity.getCreatedAt());
        ticketResponse.setStatus(ticketEntity.getStatus());
        return ticketResponse;
    }
}
