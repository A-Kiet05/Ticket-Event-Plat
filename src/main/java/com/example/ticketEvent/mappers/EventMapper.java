package com.example.ticketEvent.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.example.ticketEvent.domain.CreateEventRequest;
import com.example.ticketEvent.domain.CreateTicketTypeRequest;
import com.example.ticketEvent.domain.dto.CreateEventRequestDTO;
import com.example.ticketEvent.domain.dto.CreateEventResponseDTO;
import com.example.ticketEvent.domain.dto.CreateTicketTypeRequestDTO;
import com.example.ticketEvent.domain.dto.CreateTicketTypeResponseDTO;
import com.example.ticketEvent.domain.entities.Event;
import com.example.ticketEvent.domain.entities.TicketType;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDTO dto);

    CreateEventRequest fromDto(CreateEventRequestDTO dto);

    CreateEventResponseDTO toDto(Event event);

    // CreateTicketTypeResponseDTO toDto(TicketType ticketType);
}
