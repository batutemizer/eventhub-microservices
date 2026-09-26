package com.batutemizer.ticketservice.repository;

import com.batutemizer.ticketservice.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
        List<TicketEntity> findByUserId(Long userId);
}
