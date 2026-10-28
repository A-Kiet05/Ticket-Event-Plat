package com.example.ticketEvent.service;

import com.example.ticketEvent.domain.entities.TicketValidation;

public interface TicketValidationService{

    TicketValidation verifyByQrCodeId(UUID qrCodeId);
    TicketValidation verifyByTicketId(UUID ticketId);
    
}