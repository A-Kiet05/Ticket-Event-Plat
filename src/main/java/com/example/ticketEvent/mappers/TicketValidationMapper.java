package com.example.ticketEvent.mappers;



@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TicketValidationMapper{

    @Mapping(target = "ticketId" , source = "ticket.id")
    TicketValidationResponseDTO getTicketValidationResponseDto(TicketValidation ticketValidation);
}