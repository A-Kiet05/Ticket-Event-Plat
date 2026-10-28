package com.example.ticketEvent.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface TicketValidationRepository extends JpaRepository<TicketValidation , UUID> {
    
    
}