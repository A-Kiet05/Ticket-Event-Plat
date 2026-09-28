package com.example.ticketEvent.service.imple;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ticketEvent.domain.CreateEventRequest;
import com.example.ticketEvent.repositories.EventRepository;
import com.example.ticketEvent.repositories.UserRepository;
import com.example.ticketEvent.service.EventService;
import com.example.ticketEvent.domain.entities.*;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Override 
    public Event createEvent(UUID organizerId , CreateEventRequest eventRequest){
        
        User organizer = userRepository.findById(organizerId).orElseThrow(()-> new UsernameNotFoundException
        ( String.format("User with the ID '%s' not found", organizerId)));
       
       List<TicketType> ticketTypeCreatedList =  eventRequest.getTicketTypes().stream().map(ticketType-> {
            TicketType ticketTypeCreate = new TicketType();
            ticketTypeCreate.setName(ticketType.getName());
            ticketTypeCreate.setPrice(ticketType.getPrice());
            ticketTypeCreate.setTotalAvailable(ticketType.getTotalAvailable());
            ticketTypeCreate.setDescription(ticketType.getDescription());
            return ticketTypeCreate;
        }).toList();
        
        
        
        Event eventCreate = new Event();
        eventCreate.setName(eventRequest.getName());
        eventCreate.setOrganizer(organizer);
        eventCreate.setStart(eventRequest.getStart());
        eventCreate.setEnd(eventRequest.getEnd());
        eventCreate.setVenue(eventRequest.getVenue());
        eventCreate.setSalesStart(eventRequest.getSalesStart());
        eventCreate.setSalesEnd(eventRequest.getSalesEnd());
        eventCreate.setStatus(eventRequest.getStatus());
        eventCreate.setTicketTypes(ticketTypeCreatedList);
       
        return eventRepository.save(eventCreate);


    }
}
