package com.batutemizer.eventservice.service;

import com.batutemizer.eventservice.dto.request.EventCreateRequest;
import com.batutemizer.eventservice.dto.request.EventUpdateRequest;
import com.batutemizer.eventservice.dto.response.EventResponse;
import com.batutemizer.eventservice.entity.EventEntity;
import com.batutemizer.eventservice.exception.EventNotFoundException;
import com.batutemizer.eventservice.mapper.EventMapper;
import com.batutemizer.eventservice.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public EventResponse  createEvent(EventCreateRequest request) {

        LocalDateTime now = LocalDateTime.now();

        EventEntity entity = eventMapper.toEntity(request);
        entity.setCreatedAt(now);
        EventEntity savedEvent = eventRepository.save(entity);
        return eventMapper.toResponse(savedEvent);
    }


    public List<EventResponse> allEvents() {
        return eventRepository.findAll()
                .stream()
                .map(eventMapper::toResponse)
                .toList();
    }

    public EventResponse getEventById(Long id) {
        EventEntity foundEvent = eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException("Not Found Event"));
        return eventMapper.toResponse(foundEvent);
    }

    public EventResponse updateEvent(Long id , EventUpdateRequest request) {
        EventEntity foundEvent = eventRepository.findById(id).orElseThrow(() ->
                new EventNotFoundException("Not Found Event"));

        eventMapper.updateEntity(foundEvent, request);
        EventEntity savedEvent = eventRepository.save(foundEvent);
        return eventMapper.toResponse(savedEvent);
    }

    public void deleteEvent(Long id) {
        EventEntity foundEvent = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Not Found Event"));

        eventRepository.delete(foundEvent);
    }
}
