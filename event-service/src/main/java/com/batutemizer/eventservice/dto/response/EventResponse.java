package com.batutemizer.eventservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class EventResponse {
    private Long id;
    private String title;
    private String description;
    private String location;
    private LocalDateTime startTime;
    private Integer capacity;
    private LocalDateTime createdAt;
}
