package com.batutemizer.eventservice.mapper;

import com.batutemizer.eventservice.dto.request.EventCreateRequest;
import com.batutemizer.eventservice.dto.request.EventUpdateRequest;
import com.batutemizer.eventservice.dto.response.EventResponse;
import com.batutemizer.eventservice.entity.EventEntity;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {
    public EventEntity toEntity(EventCreateRequest request){
        EventEntity eventEntity = new EventEntity();
        eventEntity.setTitle(request.getTitle());
        eventEntity.setDescription(request.getDescription());
        eventEntity.setLocation(request.getLocation());
        eventEntity.setStartTime(request.getStartTime());
        eventEntity.setCapacity(request.getCapacity());
        return eventEntity;

    }

    public EventResponse toResponse(EventEntity entity){
        EventResponse eventResponse = new EventResponse();
        eventResponse.setId(entity.getId());
        eventResponse.setTitle(entity.getTitle());
        eventResponse.setDescription(entity.getDescription());
        eventResponse.setLocation(entity.getLocation());
        eventResponse.setStartTime(entity.getStartTime());
        eventResponse.setCapacity(entity.getCapacity());
        eventResponse.setCreatedAt(entity.getCreatedAt());
        return eventResponse;
    }

    public void updateEntity(EventEntity entity, EventUpdateRequest request){
        entity.setTitle(request.getTitle());
        entity.setDescription(request.getDescription());
        entity.setLocation(request.getLocation());
        entity.setStartTime(request.getStartTime());
        entity.setCapacity(request.getCapacity());
    }
}
