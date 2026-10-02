package com.example.ticketEvent.service.imple;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ticketEvent.domain.CreateEventRequest;
import com.example.ticketEvent.domain.UpdateEventRequest;
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
       
        Event eventCreate = new Event();

       List<TicketType> ticketTypeCreatedList =  eventRequest.getTicketTypes().stream().map(ticketType-> {
            TicketType ticketTypeCreate = new TicketType();
            ticketTypeCreate.setName(ticketType.getName());
            ticketTypeCreate.setPrice(ticketType.getPrice());
            ticketTypeCreate.setTotalAvailable(ticketType.getTotalAvailable());
            ticketTypeCreate.setDescription(ticketType.getDescription());
            ticketTypeCreate.setEvent(eventCreate);
            return ticketTypeCreate;
        }).toList();
        
        
        
        
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

    @Override 
    public Page<Event> listEventByOrganizerId(UUID organizerId , Pageable pageable){

        return eventRepository.findByOrganizerId(organizerId , pageable);
    }

    @Override 
    public Optional<Event> getEventById(UUID organizerId , UUID id){
        return eventRepository.findByIdAndOrganizerId(id, organizerId);
    }

    @Override
    @Transactional
    public Event updateEventByOrganizer(UUID organizerId , UUID id , UpdateEventRequest updateEventRequest){
        
        if(null == updateEventRequest.getId()){
            throw new EventNotFoundException(String.format("Event with id : '%s' not found", id));

        }

        if(!updateEventRequest.getId().equals(id)){

            throw new UpdateEventException(String.format("Event ID in request does not match the requested ID"));
        }

        Event existingEvent = eventRepository.findByIdAndOrganizerId(id, organizerId)
        .orElseThrow(()-> new EventNotFoundException(
                                                      String.format("Event with id : '%s' not found", id)));
        
        existingEvent.setName(updateEventRequest.getName());
        existingEvent.setStart(updateEventRequest.getStart());
        existingEvent.setEnd(updateEventRequest.getEnd());
        existingEvent.setVenue(updateEventRequest.getVenue());
        existingEvent.setSalesStart(updateEventRequest.getSalesStart());
        existingEvent.setSalesEnd(updateEventRequest.getSalesEnd());
        existingEvent.setStatus(updateEventRequest.getStatus());

       List<TicketType> updatedTicketTypes = updateEventRequest.getTicketTypes().stream().map(ticketTypeRequest -> {
            TicketType existingTicketType = existingEvent.getTicketTypes().stream()
                    .filter(ticketType -> ticketType.getId().equals(ticketTypeRequest.getId()))
                    .findFirst()
                    .orElseThrow(() -> new TicketTypeNotFoundException(
                            String.format("Ticket type with id : '%s' not found", ticketTypeRequest.getId())));

            existingTicketType.setName(ticketTypeRequest.getName());
            existingTicketType.setPrice(ticketTypeRequest.getPrice());
            existingTicketType.setTotalAvailable(ticketTypeRequest.getTotalAvailable());
            existingTicketType.setDescription(ticketTypeRequest.getDescription());
            existingTicketType.setEvent(existingEvent);
            return existingTicketType;
        }).collect(Collectors.toList());

        existingEvent.setTicketTypes(updatedTicketTypes);

        return eventRepository.save(existingEvent);
    }

    @Override 
    public void deleteEventByOrganizer(UUID organizerId , UUID id){
        
        eventRepository.findByIdAndOrganizerId(id, organizerId).ifPresent(eventRepository::delete);
    }

    @Override 
    public Page<Event> listPublishedEvents(Pageable pageable){
        return eventRepository.findByStatus(EventStatus.PUBLISHED , pageable);
    }

    @Override 
    public Optional<Event> getPublishedEventById(UUID eventId){
        return eventRepository.findByIdAndStatus(eventId, EventStatus.PUBLISHED);
    }

    @Override 
    public Page<Event> searchEvent(String searchTerm, Pageable pageable) {
        
        return eventRepository.searchEvents(searchTerm, pageable);
    }
}
