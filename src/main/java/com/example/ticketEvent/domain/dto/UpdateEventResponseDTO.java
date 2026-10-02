package com.example.ticketEvent.domain.dto;


import com.example.ticketEvent.domain.entities.EventStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEventResponseDTO {

  private UUID id;
  private String name;
  private LocalDateTime start;
  private LocalDateTime end;
  private String venue;
  private LocalDateTime salesStart;
  private LocalDateTime salesEnd;
  private EventStatus status;
  private List<CreateTicketTypeResponseDTO> ticketTypes;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  
}