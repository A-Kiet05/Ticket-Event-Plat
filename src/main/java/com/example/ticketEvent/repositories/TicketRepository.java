package com.example.ticketEvent.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket , UUID> {
    
    
    Integer countByTicketTypeId( UUID ticketTypeId);
    Page<Ticket> findByUserId(UUID userId , Pageable pageable);
    Optional<Ticket> findById(UUID ticketId);
}