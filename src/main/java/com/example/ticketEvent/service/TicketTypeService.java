package com.example.ticketEvent.service;

public interface TicketTypeService {
    // Define methods related to ticket operations here
    public Ticket purchaseTicket(UUID userId , UUID ticketTypeId );
    
}