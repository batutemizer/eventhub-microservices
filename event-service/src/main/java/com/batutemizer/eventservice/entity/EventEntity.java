package com.batutemizer.eventservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String title;
    private String description;
    private String location;
    private LocalDateTime startTime;
    private Integer capacity;
    private LocalDateTime createdAt;

}
