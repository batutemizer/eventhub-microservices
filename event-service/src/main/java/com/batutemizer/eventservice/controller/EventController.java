package com.batutemizer.eventservice.controller;

import com.batutemizer.eventservice.dto.request.EventCreateRequest;
import com.batutemizer.eventservice.dto.request.EventUpdateRequest;
import com.batutemizer.eventservice.dto.response.EventResponse;
import com.batutemizer.eventservice.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    @PostMapping
    public EventResponse createEvent(@RequestBody  EventCreateRequest request) {
        return eventService.createEvent(request);
    }

    @GetMapping
    public List<EventResponse> getEvents() {
        return eventService.allEvents();
    }

    @GetMapping("/{id}")
    public EventResponse getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    @PutMapping("/{id}")
    public EventResponse updateEvent(@PathVariable Long id, @RequestBody EventUpdateRequest request) {
        return eventService.updateEvent(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }
}
