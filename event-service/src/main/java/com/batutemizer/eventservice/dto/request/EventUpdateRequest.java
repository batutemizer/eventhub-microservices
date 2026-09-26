package com.batutemizer.eventservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventUpdateRequest {
    private String title;
    private String description;
    private String location;
    private LocalDateTime startTime;
    private Integer capacity;
}
