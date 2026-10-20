package com.example.ticketEvent.mappers;

import com.example.ticketEvent.domain.dto.ListTicketResponseDTO;
import com.example.ticketEvent.domain.entities.Ticket;
import com.example.ticketEvent.domain.entities.TicketType;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TicketMapper{

    ListTicketTicketTypeResponseDTO toListTicketTicketTypeResponseDTO(TicketType ticketType);

    ListTicketResponseDTO toListTicketResponseDTO (Ticket ticket);
}