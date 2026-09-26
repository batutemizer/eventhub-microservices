package com.batutemizer.eventservice.repository;

import com.batutemizer.eventservice.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
}
