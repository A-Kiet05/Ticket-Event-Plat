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

  
  private String name;

  private LocalDateTime start;

  private LocalDateTime end;
  
  private String venue;

  private LocalDateTime salesStart;

  private LocalDateTime salesEnd;

  private EventStatus status;

//   @NotEmpty(message = "At least one ticket type is required")
//   @Valid
  private List<CreateTicketTypeRequestDTO> ticketTypes;
  
}