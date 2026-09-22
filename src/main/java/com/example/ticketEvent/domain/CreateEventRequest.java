package com.example.ticketEvent.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.ticketEvent.domain.entities.EventStatus;
import com.example.ticketEvent.domain.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class CreateEventRequest {
    
    private String name;
    private LocalDateTime start;
    private LocalDateTime end;
    private String venue;
    private LocalDateTime salesStart;
    private LocalDateTime salesEnd;
    private EventStatus status;
    private User organizer;
    private List<CreateTicketTypeRequest> ticketTypes= new ArrayList<>();
}
