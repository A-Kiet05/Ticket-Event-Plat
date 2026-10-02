package com.example.ticketEvent.domain.dto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListPublishedEventResponseDTO {

   
    private String name;
    private String description;
    private LocalDateTime start;
    private LocalDateTime end;
    private String venue;
    private LocalDateTime salesStart;
    private LocalDateTime salesEnd;
    private EventStatus status;

    
}