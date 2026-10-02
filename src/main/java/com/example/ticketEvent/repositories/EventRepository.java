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
         Page<Event> findByStatus(EventStatus status , Pageable pageable);
         Optional<Event> findByIdAndStatus(UUID id , EventStatus status);

        @Query(value = "SELECT * FROM events WHERE " +
                        "status = 'PUBLISHED' AND " +
                        "to_tsvector('english', COALESCE(name, '') || ' ' || COALESCE(venue, '')) " +
                        "@@ plainto_tsquery('english', :searchTerm)",
                        countQuery = "SELECT count(*) FROM events WHERE " +
                            "status = 'PUBLISHED' AND " +
                            "to_tsvector('english', COALESCE(name, '') || ' ' || COALESCE(venue, '')) " +
                            "@@ plainto_tsquery('english', :searchTerm)",
                        nativeQuery = true)
         Page<Event> searchEvents (@Param ("searchTerm") String searchTerm , Pageable pageable);
}