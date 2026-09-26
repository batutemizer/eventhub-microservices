package com.batutemizer.ticketservice.controller;

import com.batutemizer.ticketservice.dto.request.TicketCreateRequest;
import com.batutemizer.ticketservice.dto.response.TicketResponse;
import com.batutemizer.ticketservice.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketResponse> createTicket(
            @RequestBody TicketCreateRequest request) {
        System.out.println("CONTROLLER'A GELDİ");

        TicketResponse response = ticketService.createTicket(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    @GetMapping public ResponseEntity<List<TicketResponse>> getMyTickets() {
        List<TicketResponse> tickets = ticketService.getMyTickets();
        return ResponseEntity.ok(tickets); }
}