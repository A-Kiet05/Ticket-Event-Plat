package com.example.ticketEvent.service;

public interface TicketService {
    // Define methods related to ticket operations here
    Page<Ticket> viewTickets (UUID userId , Pageable pageable);
    Optional<Ticket> getTicketById(UUID ticketId);
    
}