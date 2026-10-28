package com.example.ticketEvent.domain.dto;

import java.util.UUID;

import com.example.ticketEvent.domain.entities.TicketValidationStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor

public class TicketValidationResponseDTO{

    
    private UUID ticketId;
    private TicketValidationStatus status;
}