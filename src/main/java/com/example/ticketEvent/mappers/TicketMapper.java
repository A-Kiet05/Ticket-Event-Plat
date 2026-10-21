package com.example.ticketEvent.mappers;

import com.example.ticketEvent.domain.dto.GetTicketResponseDTO;
import com.example.ticketEvent.domain.dto.ListTicketResponseDTO;
import com.example.ticketEvent.domain.entities.Ticket;
import com.example.ticketEvent.domain.entities.TicketType;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TicketMapper{

    ListTicketTicketTypeResponseDTO toListTicketTicketTypeResponseDTO(TicketType ticketType);

    ListTicketResponseDTO toListTicketResponseDTO (Ticket ticket);

    @Mapping(target = "price" , source = "ticket.ticketType.price")
    @Mapping(target = "description" , source = "ticket.ticketType.description")
    @Mapping(target = "eventName" , source = "ticket.ticketType.event.name")
    @Mapping(target = "eventVenue" , source = "ticket.ticketType.event.venue")
    @Mapping(target = "eventStart" , source = "ticket.ticketType.event.start")
    @Mapping(target = "eventEnd" , source = "ticket.ticketType.event.end")
    GetTicketResponseDTO toGetTicketResponseDTO(Ticket ticket);
}