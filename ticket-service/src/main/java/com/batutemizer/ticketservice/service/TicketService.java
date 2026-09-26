package com.batutemizer.ticketservice.service;

import com.batutemizer.ticketservice.client.EventClient;
import com.batutemizer.ticketservice.dto.event.TicketCreatedEvent;
import com.batutemizer.ticketservice.dto.request.TicketCreateRequest;
import com.batutemizer.ticketservice.dto.response.TicketResponse;
import com.batutemizer.ticketservice.entity.TicketEntity;
import com.batutemizer.ticketservice.enums.TicketEnum;
import com.batutemizer.ticketservice.mapper.TicketMapper;
import com.batutemizer.ticketservice.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final EventClient eventClient;
    private final TicketEventProducer ticketEventProducer;

    public TicketResponse createTicket(TicketCreateRequest request){
        System.out.println("CREATE TICKET SERVICE ÇALIŞTI");

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        Long userId = (Long) authentication.getPrincipal();

        eventClient.getEventById(request.getEventId());

        TicketEntity entity = ticketMapper.toEntity(request);

        entity.setUserId(userId);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setStatus(TicketEnum.ACTIVE);

        TicketEntity savedEntity = ticketRepository.save(entity);
        TicketCreatedEvent event = new TicketCreatedEvent(
                savedEntity.getId(),
                savedEntity.getUserId(),
                savedEntity.getEventId()
        );

        ticketEventProducer.sendTicketCreatedEvent(event);

        return ticketMapper.toResponse(savedEntity);
    }

    public List<TicketResponse> getMyTickets(){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        Long userId2 = (Long) authentication.getPrincipal();

        return ticketRepository.findByUserId(userId2)
                .stream()
                .map(ticketMapper::toResponse)
                .toList();
    }
}
