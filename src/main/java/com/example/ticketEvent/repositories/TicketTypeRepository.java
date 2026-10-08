package com.example.ticketEvent.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface TicketTypeRepository extends JpaRepository<TicketType , UUID> {
    
    @Query("SELECT tt FROM TicketType tt WHERE tt.id = :id")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<TicketType> findByIdWithLock(UUID id);
}