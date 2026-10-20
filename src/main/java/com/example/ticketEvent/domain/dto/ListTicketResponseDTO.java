package com.example.ticketEvent.domain.dto;


@AllArgsConstructor
@NoArgsConstructor
@Data

public class ListTicketResponseDTO {

    private UUID ticketId;
    private TicketEventStatus ticketStatus;
    private ListTicketTicketTypeResponseDTO ticketType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors, getters, and setters
}