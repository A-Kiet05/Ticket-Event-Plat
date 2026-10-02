package com.example.ticketEvent.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.ticketEvent.domain.CreateEventRequest;
import com.example.ticketEvent.domain.UpdateEventRequest;
import com.example.ticketEvent.domain.entities.Event;

public interface EventService {
    
    Event createEvent(UUID organizerId ,CreateEventRequest eventRequest);
    Page<Event> listEventByOrganizerId(UUID organizerId , Pageable pageable);
    Optional<Event> getEventById(UUID organizerId , UUID id);
    Event updateEventByOrganizer(UUID organizerId , UUID id , UpdateEventRequest updateEventRequest);
    void deleteEventByOrganizer(UUID organizerId , UUID id);
    Page<Event> listPublishedEvents(Pageable pageable);
    Optional<Event> getPublishedEventById(UUID eventId);
    Optional<Event> searchEvent(String searchTerm, Pageable pageable);
    
}
