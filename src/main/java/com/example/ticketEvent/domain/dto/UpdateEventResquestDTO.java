package com.example.ticketEvent.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

import com.example.ticketEvent.domain.entities.EventStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEventRequestDTO {

  @NonNull(message = "Event ID is required")
  private UUID id;
  
  @NonBlank(message = "Event name is required")
  private String name;
  
  private LocalDateTime start;

  private LocalDateTime end;
  
  @NotBlank(message = "Venue information is required")
  private String venue;

  private LocalDateTime salesStart;

  private LocalDateTime salesEnd;
  
  @NonNull(message = "Event status must be provided")
  private EventStatus status;

  @NotEmpty(message = "At least one ticket type is required")
  @Valid
  private List<CreateTicketTypeRequestDTO> ticketTypes;
  
}