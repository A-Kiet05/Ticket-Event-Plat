package com.example.ticketEvent.service;

import com.example.ticketEvent.domain.CreateEventRequest;
import com.example.ticketEvent.domain.entities.Event;

public interface EventService {
    
    Event createEvent(CreateEventRequest eventRequest);
}
