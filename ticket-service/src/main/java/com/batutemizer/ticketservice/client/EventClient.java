package com.batutemizer.ticketservice.client;

import com.batutemizer.ticketservice.dto.response.EventResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "event-service",url = "http://localhost:8081")
public interface EventClient {

    @GetMapping("/events/{id}")
    EventResponse getEventById(@PathVariable Long id);


}
