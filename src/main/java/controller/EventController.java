package controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticketEvent.domain.CreateEventRequest;
import com.example.ticketEvent.domain.dto.CreateEventRequestDTO;
import com.example.ticketEvent.domain.dto.CreateEventResponseDTO;
import com.example.ticketEvent.domain.entities.Event;
import com.example.ticketEvent.mappers.EventMapper;
import com.example.ticketEvent.service.EventService;

import jakarta.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping (path = "/api/v1/events")
@RequiredArgsConstructor
public class EventController {

  private final EventMapper eventMapper;
  private final EventService eventService;

  @PostMapping
  public ResponseEntity<CreateEventResponseDTO> createEvent(
      @AuthenticationPrincipal Jwt jwt,
      @Valid @RequestBody CreateEventRequestDTO createEventRequestDto) {
            CreateEventRequest createEventRequest = eventMapper.fromDto(createEventRequestDto);
            UUID userId = UUID.fromString(jwt.getSubject());

            Event createdEvent = eventService.createEvent(userId, createEventRequest);
            CreateEventResponseDTO createEventResponseDto = eventMapper.toDto(createdEvent);
            return new ResponseEntity<>(createEventResponseDto, HttpStatus.CREATED);
      }
      

  
}
