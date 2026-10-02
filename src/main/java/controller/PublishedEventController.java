package com.example.ticketEvent.controller;

import java.util.Optional;

import org.w3c.dom.events.Event;

import com.example.ticketEvent.service.EventService;

@RestController
@RequestMapping (path = "/api/v1/published-events")
@RequiredArgsConstructor
public class PublishedEventController {
    
    private final EventService eventService;
    private final EventMapper eventMapper;

    @GetMapping
    public ResponseEntity<Page<ListPublishedEventResponseDTO>> listPublishedEvents(Pageable pageable){
        Page<Event> events = eventService.listPublishedEvents(pageable);
        return ResponseEntity.ok(events.map(eventMapper::toListPublishedEventDto));
    }

    @GetMapping(path = "/{eventId}")
    public ResponseEntity<EventDetailsResponseDTO> getPublishedEventById(@PathVariable UUID eventId)
    {
        Optional<Event> eventOptional = eventService.getPublishedEventById(eventId);
        if (eventOptional.isPresent()){
            EventDetailsResponseDTO eventDetailsResponseDTO = eventMapper.toEventDetailsResponseDto(eventOptional.get());
            return ResponseEntity.ok(eventDetailsResponseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/search")
    public ResponseEntity<Page<ListPublishedEventResponseDTO>> searchEvents(@RequestParam String searchTerm , Pageable pageable){

        Page<Event> events ;
        if(null != searchTerm && !searchTerm.trim().isEmpty()){

            events = eventService.searchEvent(searchTerm, pageable);
            
        }
        else{
            events = eventService.listPublishedEvents(pageable);
            
        }
        
       return ResponseEntity.ok(events.map(eventMapper::toListPublishedEventDto));
    }

}