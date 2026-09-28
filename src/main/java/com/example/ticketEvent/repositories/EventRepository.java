package com.example.ticketEvent.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ticketEvent.domain.entities.Event;

@Repository 
public interface EventRepository extends JpaRepository<Event , UUID>{
         
}