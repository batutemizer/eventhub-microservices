package com.batutemizer.ticketservice.entity;

import com.batutemizer.ticketservice.enums.TicketEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private Long userId;
    private Long eventId;
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private TicketEnum status;
}
