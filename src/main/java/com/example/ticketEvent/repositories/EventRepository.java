package com.example.ticketEvent.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ticketEvent.domain.entities.Event;

@Repository 
public interface EventRepository extends JpaRepository<Event , UUID>{

         Page<Event> findByOrganizerId(UUID id , Pageable pageable);
         Optional<Event> findByIdAndOrganizerId (UUID id , UUID organizerId);
}