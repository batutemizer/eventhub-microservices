package com.batutemizer.ticketservice.dto.response;

import com.batutemizer.ticketservice.enums.TicketEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponse {

    private Long id ;
    private Long eventId;
    private LocalDateTime createdAt;
    private TicketEnum status;

}
