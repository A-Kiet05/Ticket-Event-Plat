package com.example.ticketEvent.service;

import java.util.UUID;

import com.example.ticketEvent.domain.CreateEventRequest;
import com.example.ticketEvent.domain.entities.Event;

public interface EventService {
    
    Event createEvent(UUID organizerId ,CreateEventRequest eventRequest);
}
