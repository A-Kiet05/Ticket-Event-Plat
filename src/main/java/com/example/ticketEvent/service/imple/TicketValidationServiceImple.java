package com.example.ticketEvent.service.imple;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.example.ticketEvent.service.EventService;
import com.example.ticketEvent.service.TicketValidationService;
import com.example.ticketEvent.domain.entities.*;
import com.example.ticketEvent.repositories.QrCodeRepository;
import com.example.ticketEvent.repositories.TicketRepository;
import com.example.ticketEvent.repositories.TicketValidationRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class TicketValidationServiceImple implements TicketValidationService{

    private final TicketValidationRepository ticketValidationRepository;
    private final QrCodeRepository qrCodeRepository;
    private final TicketRepository ticketRepository;
    
    @Override 
    public  TicketValidation verifyByQrCodeId(UUID qrCodeId){
          QrCode qrCode = qrCodeRepository.findByIdAndStatus(qrCodeId , QrCodeStatus.ACTIVE)
          .orElseThrow(()-> new QrCodeNotFoundException
                      (String.format("Qr Code with the ID '%s' not found", qrCodeId)));
                
        Ticket ticket = qrCode.getTicket();
        return validate(ticket);

    }



    @Override 
    public TicketValidation verifyByTicketId(UUID ticketId){
         
       Ticket ticket = ticketRepository.findById(ticketId)
        .orElseThrow(()-> new QrCodeNotFoundException
                      (String.format("Ticket with the ID '%s' not found", ticketId)));
        
        return validate(ticket) ;
    }

    private TicketValidation validate(Ticket ticket){

        TicketValidation ticketValidation = new TicketValidation();
        ticketValidation.setTicket(ticket);
        ticketValidation.setValidationMethod(TicketValidationMethod.QR_CODE);

        TicketValidaionStatus ticketValidationStatus =  ticket.getValidations().stream()
        .filter( v -> TicketValidationStatus.VALID.equals(v.getStatus()) )
        .findFirst()
        .map(v-> TicketValidationStatus.INVALID)
        .orElse(TicketValidationStatus.VALID);

        ticketValidation.setStatus(ticketValidationStatus);
        return ticketValidationRepository.save(ticketValidation);
    }
}