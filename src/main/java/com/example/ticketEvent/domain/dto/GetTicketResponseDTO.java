package com.example.ticketEvent.domain.dto;

@Data 
@AllArgsConstructor 
@NoArgsConstructor
public class GetTicketResponseDTO{
    
     private UUID id;
     private TicketEventStatus status;
     private Double price;
     private String description;
     private String eventVenue;
     private String eventName;
     private LocalDateTime eventStart;
     private LocalDateTime eventEnd;
}